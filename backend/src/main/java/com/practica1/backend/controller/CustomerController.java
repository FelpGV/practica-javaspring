package com.practica1.backend.controller;

import com.practica1.backend.dto.CustomerSpendDTO;
import com.practica1.backend.exception.HandledErrorResponse;
import com.practica1.backend.model.entity.Customer;
import com.practica1.backend.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customers found", content = @Content(schema = @Schema(implementation = Customer.class))),
            @ApiResponse(responseCode = "404", description = "Customers not found", content = @Content(schema = @Schema(implementation = HandledErrorResponse.class)))
    })
    @Operation(summary = "Get all Customers")
    public List<Customer> getAll() {
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer found", content = @Content(schema = @Schema(implementation = Customer.class))),
            @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content(schema = @Schema(implementation = HandledErrorResponse.class)))
    })
    @Operation(summary = "Get a Customer by id")
    public Customer getById(@PathVariable long id) {
        return customerService.getById(id);
    }

    @PostMapping("/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Customer created", content = @Content(schema = @Schema(implementation = Customer.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Customer data", content = @Content(schema = @Schema(implementation = HandledErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Customer email already exists", content = @Content(schema = @Schema(implementation = HandledErrorResponse.class)))
    })
    @Operation(summary = "Create a new Customer")
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {
        Customer customerSaved = customerService.addCustomer(customer);
        return new ResponseEntity<>(customerSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer updated", content = @Content(schema = @Schema(implementation = Customer.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Customer data", content = @Content(schema = @Schema(implementation = HandledErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Customer not found", content = @Content(schema = @Schema(implementation = HandledErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Customer email already exists", content = @Content(schema = @Schema(implementation = HandledErrorResponse.class)))
    })
    @Operation(summary = "Update a Customer by id")
    public ResponseEntity<Customer> updateCustomer(@Valid @PathVariable Long id, @RequestBody Customer customer) {
        Customer customerUpdated = customerService.updateCustomer(id, customer);
        return new ResponseEntity<>(customerUpdated, HttpStatus.OK);
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
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Total spend of all Customers", content = @Content(schema = @Schema(implementation = CustomerSpendDTO.class))),
            @ApiResponse(responseCode = "404", description = "Customers not found", content = @Content(schema = @Schema(implementation = HandledErrorResponse.class))),
    })
    @Operation(summary = "Get the total spend of all Customers")
    public List<CustomerSpendDTO> getCustomerSpend() {
        return customerService.getCustomerSpend();
    }

    @GetMapping("/spend")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Total spend of all Customers with pagination", content = @Content(schema = @Schema(implementation = CustomerSpendDTO.class))),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content(schema = @Schema(implementation = HandledErrorResponse.class))),
    })
    @Operation(summary = "Get the total spend of all Customers with pagination")
    public Page<CustomerSpendDTO> getCustomerSpendPaginate(@ParameterObject @PageableDefault(size = 20) Pageable page) {
        return customerService.getCustomerSpendPaginate(page);
    }


}
