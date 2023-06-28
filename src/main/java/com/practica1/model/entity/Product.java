package com.practica1.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long idProduct;

    @Column(length = 45, nullable = false)
    private String name;

    @Column(length = 150, nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(length = 30, nullable = false)
    private String category;

    @Column(nullable = false)
    private Integer stock;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<InvoiceProduct> invoiceProduct;
}