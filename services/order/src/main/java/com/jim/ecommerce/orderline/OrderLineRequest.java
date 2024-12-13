package com.jim.ecommerce.orderline;

public record OrderLineRequest(Integer id, Integer orderId, Integer productId, double quantity){}
