package com.practica1.service;

import com.practica1.dto.CustomerSpendDTO;
import com.practica1.model.entity.Customer;
import com.practica1.model.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Customer not found with id " + id));
    }

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer addCustomer(Customer customer) {
        if (customer.getEmail() == null || customer.getEmail().isEmpty()) {
            throw new NullPointerException("Customer email is required");
        }
        if (customer.getName() == null || customer.getName().isEmpty()) {
            throw new NullPointerException("Customer name is required");
        }
        Optional<Customer> existingCustomer = customerRepository.findByEmail(customer.getEmail());
        if (existingCustomer.isPresent()) {
            throw new DataIntegrityViolationException("Customer with email " + customer.getEmail() + " already exists");
        }
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

    public void delete(long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isEmpty()) {
            throw new EntityNotFoundException("Customer not found with id " + id);
        }
        customerRepository.deleteById(id);
    }

    public List<CustomerSpendDTO> getCustomerSpend() {
        return customerRepository.calculateTotalSpendForAllCustomers();
    }

    public Page<CustomerSpendDTO> getCustomerSpendPaginate(Pageable pageable) {
        return customerRepository.getCustomerSpendPaginate(pageable);
    }
}
