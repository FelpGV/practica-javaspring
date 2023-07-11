package com.practica1.backend.service;

import com.practica1.backend.dto.CartDTO;
import com.practica1.backend.dto.CartProductDTO;
import com.practica1.backend.model.entity.Customer;
import com.practica1.backend.model.entity.Invoice;
import com.practica1.backend.model.entity.InvoiceProduct;
import com.practica1.backend.model.entity.Product;
import com.practica1.backend.model.repository.InvoiceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    InvoiceRepository invoiceRepository;
    CustomerService customerService;
    ProductService productService;
    InvoiceProductService invoiceProductService;

    public InvoiceService(InvoiceRepository invoiceRepository, CustomerService customerService, ProductService productService, InvoiceProductService invoiceProductService) {
        this.invoiceRepository = invoiceRepository;
        this.customerService = customerService;
        this.productService = productService;
        this.invoiceProductService = invoiceProductService;
    }

    public List<Invoice> getAll() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> getById(Long id) {
        return invoiceRepository.findById(id);
    }

    public void addInvoice(CartDTO cartDTO) {
        Customer customer;
        System.out.println(cartDTO.getIdCustomer());
        try {
            customer = customerService.getById(cartDTO.getIdCustomer());
        } catch (Exception e) {
            throw new EntityNotFoundException("Customer not found");
        }

        Invoice invoice = new Invoice();
        invoice.setCustomer(customer);
        invoice.setDate(LocalDateTime.now());
        invoice.setTotal(0.0);

        invoice.setInvoiceProduct(new ArrayList<>());
        invoice = invoiceRepository.save(invoice);

        for (CartProductDTO productDTO : cartDTO.getProducts()) {
            Product product = productService.getById(productDTO.getIdProduct());

            InvoiceProduct invoiceProduct = new InvoiceProduct();
            invoiceProduct.setIdInvoice(invoice.getIdInvoice());
            invoiceProduct.setIdProduct(product.getIdProduct());
            invoiceProduct.setQuantity(productDTO.getQuantity());
            invoiceProduct.setInvoice(invoice);
            invoiceProduct.setProduct(product);

            invoice.setTotal(invoice.getTotal() + productDTO.getQuantity() * product.getPrice());

            invoice.getInvoiceProduct().add(invoiceProduct);

            productService.updateProductStock(product.getIdProduct(), productDTO.getQuantity());
            invoiceProductService.addInvoiceProduct(invoiceProduct);
        }

        invoiceRepository.save(invoice);

    }

    public void totalActualization() {
        List<Invoice> invoices = invoiceRepository.findAll();
        for (Invoice invoice : invoices) {
            double total = 0;
            for (InvoiceProduct invoiceProduct : invoice.getInvoiceProduct()) {
                total += invoiceProduct.getQuantity() * invoiceProduct.getProduct().getPrice();
            }
            invoice.setTotal(total);
            invoiceRepository.save(invoice);
        }
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
