package com.practica1.backend.service;

import com.practica1.backend.model.entity.InvoiceProduct;
import com.practica1.backend.model.repository.InvoiceProductRepository;
import org.springframework.stereotype.Service;

@Service
public class InvoiceProductService {
    InvoiceProductRepository invoiceProductRepository;

    public InvoiceProductService(InvoiceProductRepository invoiceProductRepository) {
        this.invoiceProductRepository = invoiceProductRepository;
    }

    public void addInvoiceProduct(InvoiceProduct invoiceProduct) {
        invoiceProductRepository.save(invoiceProduct);
    }
}
