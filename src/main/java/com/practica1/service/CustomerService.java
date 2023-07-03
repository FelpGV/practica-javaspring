package com.practica1.service;

import com.practica1.model.entity.Customer;
import com.practica1.model.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found with id " + id));
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(long id, Customer customer) {
        return customerRepository.findById(id).
                map(customerToUpdate -> {
                    customerToUpdate.setName(customer.getName());
                    customerToUpdate.setEmail(customer.getEmail());
                    return customerRepository.save(customerToUpdate);
                }).orElseThrow(() -> new RuntimeException("Customer not found with id " + id));
    }

    public void delete(long l) {
        customerRepository.deleteById(l);
    }
}
