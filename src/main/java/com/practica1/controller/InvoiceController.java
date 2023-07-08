package com.practica1.controller;

import com.practica1.dto.CartDTO;
import com.practica1.service.InvoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @Operation(summary = "DTO: CartDTO, Create a new Invoice and InvoiceProduct")
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void addInvoice(@RequestBody(description = "Parameters to add a new Invoice") CartDTO cartDTO) {
        invoiceService.addInvoice(cartDTO);
    }


    @PutMapping("/")
    public void totalActualization() {
        invoiceService.totalActualization();
    }

}
