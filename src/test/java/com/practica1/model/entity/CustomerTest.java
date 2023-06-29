package com.practica1.model.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {
    @Test
    public void testCustomer() {
        Customer customer = new Customer();
        customer.setIdCustomer(123L);
        customer.setName("Name");
        customer.setEmail("test@mail.com");

        assertEquals(123L, customer.getIdCustomer());
        assertEquals("Name", customer.getName());
        assertEquals("test@mail.com", customer.getEmail());
    }
}
