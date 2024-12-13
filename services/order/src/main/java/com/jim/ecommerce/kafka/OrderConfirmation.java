package com.jim.ecommerce.kafka;

import com.jim.ecommerce.customer.CustomerResponse;
import com.jim.ecommerce.order.PaymentMethod;
import com.jim.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(String orderReference,
                                BigDecimal totalAmount,
                                PaymentMethod paymentMethod,
                                CustomerResponse customer,
                                List<PurchaseResponse> products) {
}