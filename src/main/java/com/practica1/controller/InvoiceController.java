package com.practica1.controller;

import com.practica1.dto.CartDTO;
import com.practica1.model.entity.Customer;
import com.practica1.service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
@Tag(name = "Invoice", description = "Invoice and InvoiceProduct API")
public class InvoiceController {
    InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Invoice created", content = @Content(schema = @Schema(implementation = Customer.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Invoice data", content = @Content(schema = @Schema(implementation = Customer.class))),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content(schema = @Schema(implementation = Customer.class)))
    })
    @Operation(summary = "DTO: CartDTO, Create a new Invoice and InvoiceProduct",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Parameters to add a new Invoice", required = true))
    public void addInvoice(@Valid @RequestBody CartDTO cartDTO) {
        invoiceService.addInvoice(cartDTO);
    }


    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Actualize the total of all Invoices")
    public void totalActualization() {
        invoiceService.totalActualization();
    }

}
