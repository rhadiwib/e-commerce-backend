package com.jim.ecommerce.customer;

import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer toCustomer(CustomerRequest request) {
        if (request == null) {
            return null;
        }
        return Customer.builder()
                .id(request.id())
                .firstname(request.firstName())
                .lastname(request.lastName())
                .email(request.email())
                .address(request.address())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
