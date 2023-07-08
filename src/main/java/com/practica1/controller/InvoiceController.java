package com.practica1.controller;

import com.practica1.dto.CartDTO;
import com.practica1.service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    @Operation(summary = "DTO: CartDTO, Create a new Invoice and InvoiceProduct")
    public void addInvoice(@RequestBody(description = "Parameters to add a new Invoice", required = true) CartDTO cartDTO) {
        invoiceService.addInvoice(cartDTO);
    }


    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Actualize the total of all Invoices")
    public void totalActualization() {
        invoiceService.totalActualization();
    }

}
