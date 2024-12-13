package com.jim.ecommerce.payment;

import com.jim.ecommerce.customer.CustomerResponse;
import com.jim.ecommerce.order.PaymentMethod;
import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
