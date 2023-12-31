package com.practica1.backend.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "invoice_product")
@IdClass(InvoiceProductId.class)
@Getter
@Setter
@NoArgsConstructor
public class InvoiceProduct {

    @Id
    @Column(name = "id_invoice", nullable = false)
    private Long idInvoice;

    @Id
    @Column(name = "id_product", nullable = false)
    private Long idProduct;

    @Column(nullable = false, length = 3)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "id_invoice", insertable = false, updatable = false)
    @JsonIgnore
    private Invoice invoice;

    @ManyToOne
    @JoinColumn(name = "id_product", insertable = false, updatable = false)
    @JsonIgnore
    private Product product;
}

