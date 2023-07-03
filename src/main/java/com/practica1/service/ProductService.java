package com.practica1.service;

import com.practica1.model.entity.Product;
import com.practica1.model.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(long id, Product product) {
        Optional<Product> productToUpdate = productRepository.findById(id);
        if (productToUpdate.isPresent()) {
            Product productUpdated = productToUpdate.get();
            productUpdated.setName(product.getName());
            productUpdated.setPrice(product.getPrice());
            productUpdated.setStock(product.getStock());
            return productRepository.save(productUpdated);
        }
        return null;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }


}
