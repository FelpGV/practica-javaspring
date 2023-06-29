package com.practica1.model.repository;


import com.practica1.model.entity.Customer;
import com.practica1.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;


    @BeforeEach
    void setUp() {
        Customer customer = new Customer();
        customer.setIdCustomer(1L);
        customer.setName("Name1");
        customer.setEmail("test1@mail.com");

        Customer customer2 = new Customer();
        customer2.setIdCustomer(2L);
        customer2.setName("Name2");
        customer2.setEmail("test2@mail.com");

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

    }


    @Test
    public void testFindAll() {
        List<Customer> customers = customerService.getAll();
        verify(customerRepository, times(1)).findAll();

        assertEquals(1L, customers.get(0).getIdCustomer());
        assertEquals("Name1", customers.get(0).getName());
        assertEquals("test1@mail.com", customers.get(0).getEmail());

        assertEquals(2L, customers.get(1).getIdCustomer());
        assertEquals("Name2", customers.get(1).getName());
        assertEquals("test2@mail.com", customers.get(1).getEmail());
    }

    @Test
    public void testFindById() {
        Customer customer = customerService.getById(1L);
        verify(customerRepository, times(1)).findById(1L);
        assertEquals(1L, customer.getIdCustomer());
        assertEquals("Name1", customer.getName());
        assertEquals("test1@mail.com", customer.getEmail());
    }
}
