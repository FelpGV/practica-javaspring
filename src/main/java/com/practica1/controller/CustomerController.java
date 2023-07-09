package com.practica1.controller;

import com.practica1.dto.CustomerSpendDTO;
import com.practica1.model.entity.Customer;
import com.practica1.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@Tag(name = "Customer", description = "Customer API")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all Customers")
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get a Customer by id")
    public Customer getById(@PathVariable long id) {
        return customerService.getById(id);
    }

    @PostMapping("/")
    @Operation(summary = "Create a new Customer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        try {
            Customer customerSaved = customerService.addCustomer(customer);
            return new ResponseEntity<>(customerSaved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a Customer by id")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        try {
            Customer customerUpdated = customerService.updateCustomer(id, customer);
            return new ResponseEntity<>(customerUpdated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Customer by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Customer deleted"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/spendGeneral")
    @Operation(summary = "Get the total spend of all Customers")
    public List<CustomerSpendDTO> getCustomerSpend() {
        return customerService.getCustomerSpend();
    }

    @GetMapping("/spend")
    @Operation(summary = "Get the total spend of all Customers with pagination")
    public Page<CustomerSpendDTO> getCustomerSpendPaginate(@ParameterObject @PageableDefault(size = 20) Pageable page) {
        return customerService.getCustomerSpendPaginate(page);
    }

}
