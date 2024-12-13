package com.jim.ecommerce.order;

import com.jim.ecommerce.customer.CustomerClient;
import com.jim.ecommerce.exception.BusinessException;
import com.jim.ecommerce.kafka.OrderConfirmation;
import com.jim.ecommerce.kafka.OrderProducer;
import com.jim.ecommerce.orderline.OrderLineRequest;
import com.jim.ecommerce.orderline.OrderLineService;
import com.jim.ecommerce.payment.PaymentClient;
import com.jim.ecommerce.payment.PaymentRequest;
import com.jim.ecommerce.product.ProductClient;
import com.jim.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final CustomerClient customerClient;
    private final PaymentClient paymentClient;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    @Transactional
    public Integer createOrder(OrderRequest request) {
        /*
          1. check the customer is available or not with openFeign.
          2. purchase the product via product microservice.
          3. persist the Order.
          4. persist the OrderLine.
          5. init payment process. via payment microservice.
          6. send the order confirmation to notification microservice (kafka-topic).
         **/
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with the provided ID"));

        var purchasedProducts = productClient.purchaseProducts(request.products());

        var order = this.repository.save(mapper.toOrder(request));

        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(new OrderLineRequest(null,
                                                                    order.getId(),
                                                                    purchaseRequest.productId(),
                                                                    purchaseRequest.quantity()));
        }

        var paymentRequest = new PaymentRequest(request.amount(),
                                                request.paymentMethod(),
                                                order.getId(),
                                                order.getReference(),
                                                customer);
        paymentClient.requestOrderPayment(paymentRequest);

        orderProducer.sendOrderConfirmation(new OrderConfirmation(
                                            request.reference(),
                                            request.amount(),
                                            request.paymentMethod(),
                                            customer,
                                            purchasedProducts));
        return order.getId();
    }

    public List<OrderResponse> findAllOrders() {
        return this.repository.findAll()
                              .stream()
                              .map(this.mapper::fromOrder)
                              .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer id) {
        return this.repository.findById(id)
                              .map(this.mapper::fromOrder)
                              .orElseThrow(() -> new EntityNotFoundException(
                                                     String.format("Order not found with the provided ID: %d", id)));
    }
}
