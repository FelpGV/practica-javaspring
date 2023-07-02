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

    private Customer customer;
    private Customer customer2;
    private List<Customer> customers;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setIdCustomer(1L);
        customer.setName("Name1");
        customer.setEmail("test1@mail.com");

        customer2 = new Customer();
        customer2.setIdCustomer(2L);
        customer2.setName("Name2");
        customer2.setEmail("test2@mail.com");

        customers = Arrays.asList(customer, customer2);
    }


    @Test
    public void testFindAll() {
        when(customerRepository.findAll()).thenReturn(customers);

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
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Customer Customer = customerService.getById(1L);

        verify(customerRepository, times(1)).findById(1L);
        assertEquals(1L, Customer.getIdCustomer());
        assertEquals("Name1", Customer.getName());
        assertEquals("test1@mail.com", Customer.getEmail());
    }

    @Test
    public void testAddCustomer() {
        Customer customer3 = new Customer();
        customer3.setIdCustomer(3L);
        customer3.setName("name3");
        customer3.setEmail("test3@mail.com");

        when(customerRepository.save(customer3)).thenReturn(customer3);

        Customer addedCustomer = customerService.addCustomer(customer3);

        verify(customerRepository, times(1)).save(customer3);

        assertEquals(3L, addedCustomer.getIdCustomer());
        assertEquals("name3", addedCustomer.getName());
        assertEquals("test3@mail.com", addedCustomer.getEmail());
    }

    @Test
    public void testUpdateCustomer() {
        Customer customerToUpdate = new Customer();
        customerToUpdate.setIdCustomer(1L);
        customerToUpdate.setName("name3");
        customerToUpdate.setEmail("test3@mail.com");

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customerToUpdate));
        when(customerRepository.save(any(Customer.class))).thenReturn(customerToUpdate);

        Customer updatedCustomer = customerService.update(1L, customerToUpdate);

        verify(customerRepository, times(1)).save(any(Customer.class));

        assertEquals(1L, updatedCustomer.getIdCustomer());
        assertEquals(customerToUpdate.getName(), updatedCustomer.getName());
        assertEquals(customerToUpdate.getEmail(), updatedCustomer.getEmail());
    }

    @Test
    public void testDeleteCustomer() {
        doNothing().when(customerRepository).deleteById(1L);
        customerService.delete(1L);
        verify(customerRepository, times(1)).deleteById(1L);
    }
}
