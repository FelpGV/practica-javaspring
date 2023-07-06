package com.practica1.service;

import com.practica1.dto.CustomerSpendDTO;
import com.practica1.model.entity.Customer;
import com.practica1.model.repository.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public List<CustomerSpendDTO> getCustomerSpend() {
        return customerRepository.calculateTotalSpendForAllCustomers();
    }

    public Page<CustomerSpendDTO> getCustomerSpendPaginate(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 5);
        return customerRepository.getCustomerSpendPaginate(pageable);
    }
}
