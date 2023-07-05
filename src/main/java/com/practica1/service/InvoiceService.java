package com.practica1.service;

import com.practica1.model.entity.Invoice;
import com.practica1.model.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> getAll() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> getById(Long id) {
        return invoiceRepository.findById(id);
    }

    public Invoice addInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public Invoice updateInvoice(long id, Invoice invoice) {
        Optional<Invoice> InvoiceToUpdate = invoiceRepository.findById(id);
        if (InvoiceToUpdate.isPresent()) {
            Invoice InvoiceUpdated = InvoiceToUpdate.get();
            InvoiceUpdated.setDate(invoice.getDate());
            InvoiceUpdated.setTotal(invoice.getTotal());
            return invoiceRepository.save(InvoiceUpdated);
        }
        return null;
    }
}
