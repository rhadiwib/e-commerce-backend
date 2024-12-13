package com.jim.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(String id,
                              @NotNull(message = "Customer firstname is mandatory")
                              String firstName,
                              @NotNull(message = "Customer lastname is mandatory")
                              String lastName,
                              @NotNull(message = "Customer email is mandatory")
                              @Email(message = "Customer email is not valid email address")
                              String email,
                              Address address) {
}