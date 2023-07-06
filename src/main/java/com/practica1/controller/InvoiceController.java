package com.practica1.controller;

import com.practica1.dto.CartDTO;
import com.practica1.service.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {
    InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void addInvoice(@RequestBody CartDTO cartDTO) {
        invoiceService.addInvoice(cartDTO);
    }

}
