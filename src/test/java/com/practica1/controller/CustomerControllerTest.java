package com.practica1.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.practica1.model.entity.Customer;
import com.practica1.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    private Customer customer;
    private List<Customer> customers;

    @BeforeEach
    void setUp() {
        customer = new Customer();
        customer.setIdCustomer(1L);
        customer.setName("Name1");
        customer.setEmail("test1@mail.com");

        Customer customer2 = new Customer();
        customer2.setIdCustomer(2L);
        customer2.setName("Name2");
        customer2.setEmail("test2@mail.com");

        customers = Arrays.asList(customer, customer2);

    }

    @Test
    public void testGetAll() throws Exception {
        Mockito.when(customerService.getAll()).thenReturn(customers);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/customers/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));


        verify(customerService).getAll();
    }

    @Test
    public void testGetById() throws Exception {
        when(customerService.getById(Mockito.anyLong())).thenReturn(customer);

        mockMvc.perform(get("/api/customers/1"))
                .andExpect((status().isOk()))
                .andExpect(jsonPath("$.idCustomer", is(1)))
                .andExpect(jsonPath("$.name", is("Name1")))
                .andExpect(jsonPath("$.email", is("test1@mail.com")));

        verify(customerService).getById(1L);
    }

    @Test
    public void testGetByIdThrowsException() {
        long id = 3L;

        when(customerService.getById(Mockito.anyLong())).thenThrow(new RuntimeException("Customer not found with id " + id));

        assertThrows(RuntimeException.class, () -> customerService.getById(id));
    }


    @Test
    public void testAdd() throws Exception {
        Customer customerToAdd = new Customer();
        customerToAdd.setName("name3");
        customerToAdd.setEmail("test3@mail.com");

        when(customerService.addCustomer(Mockito.any(Customer.class))).thenReturn(customerToAdd);
        ObjectMapper objectMapper = new ObjectMapper();

        ResultActions response = mockMvc.perform(post("/api/customers/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerToAdd)));

        response.andExpect(status().isCreated());
    }

    @Test
    public void testUpdate() throws Exception {
        Customer customerToUpdate = new Customer();
        customerToUpdate.setName("name3");
        customerToUpdate.setEmail("test3@mail.com");

        when(customerService.updateCustomer(Mockito.anyLong(), Mockito.any(Customer.class))).thenReturn(customerToUpdate);

        ObjectMapper objectMapper = new ObjectMapper();

        ResultActions response = mockMvc.perform(put("/api/customers/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerToUpdate)));

        response.andExpect(status().isOk());
    }

    @Test
    public void testDelete() throws Exception {
        doNothing().when(customerService).delete(1L);
        mockMvc.perform(delete("/api/customers/1"))
                .andExpect(status().isNoContent());
        verify(customerService, times(1)).delete(1L);
    }
}
