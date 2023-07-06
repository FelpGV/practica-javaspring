package com.practica1.controller;

import com.practica1.model.entity.Product;
import com.practica1.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/")
    public Iterable<Product> getAll() {
        return productService.getAll();
    }

    @RequestMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PostMapping("/")
    public ResponseEntity<Product> addProduct(Product product) {
        try {
            Product productSaved = productService.addProduct(product);
            return ResponseEntity.ok(productSaved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product product) {
        try {
            Product productUpdated = productService.updateProduct(id, product);
            return ResponseEntity.ok(productUpdated);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }


}
