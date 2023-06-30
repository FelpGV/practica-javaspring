package com.practica1.service;

import com.practica1.model.entity.Customer;
import com.practica1.model.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer getById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id " + id));
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer update(long id, Customer customer) {
        Customer customerToUpdate;
        try {
            customerToUpdate = getById(id);
        } catch (Exception e) {
            throw new RuntimeException("Customer not found with id " + id);
        }
        customerToUpdate.setName(customer.getName());
        customerToUpdate.setEmail(customer.getEmail());

        return customerRepository.save(customerToUpdate);
    }
}
