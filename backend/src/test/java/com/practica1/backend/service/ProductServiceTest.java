package com.practica1.backend.service;

import com.practica1.backend.model.entity.Product;
import com.practica1.backend.model.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    List<Product> products = new ArrayList<>();
    Product product = new Product();

    @BeforeEach
    void setUp() {
        product.setIdProduct(1L);
        product.setName("Name1");
        product.setDescription("Description1");
        product.setPrice(1.0);
        product.setCategory("Category1");
        product.setStock(1);

        Product product2 = new Product();
        product2.setIdProduct(2L);
        product2.setName("Name2");
        product2.setDescription("Description2");
        product2.setPrice(2.0);
        product2.setCategory("Category2");
        product2.setStock(2);

        products.add(product);
        products.add(product2);

    }

    @Test
    public void testFindAll() {
        when(productRepository.findAll()).thenReturn(products);

        List<Product> products = productService.getAll();

        verify(productRepository, times(1)).findAll();

        assertEquals(1L, products.get(0).getIdProduct());
        assertEquals("Name1", products.get(0).getName());

        assertEquals(2L, products.get(1).getIdProduct());
        assertEquals("Name2", products.get(1).getName());
    }

    @Test
    public void testFindById() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Product productFound = productService.getById(1L);
        assertEquals(1L, productFound.getIdProduct());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void testAddProduct() {
        Product product3 = new Product();
        product3.setIdProduct(3L);
        product3.setName("Name3");
        product3.setDescription("Description3");
        product3.setPrice(3.0);
        product3.setCategory("Category3");
        product3.setStock(3);

        when(productRepository.save(product3)).thenReturn(product3);

        Product productAdded = productService.addProduct(product3);

        assertEquals(3L, productAdded.getIdProduct());
        assertEquals("Name3", productAdded.getName());
        assertEquals("Description3", productAdded.getDescription());

        verify(productRepository, times(1)).save(product3);
    }

    @Test
    public void testUpdateProduct() {
        Product productToUpdate = new Product();
        productToUpdate.setIdProduct(1L);
        productToUpdate.setName("Name3");
        productToUpdate.setDescription("Description3");
        productToUpdate.setPrice(3.0);
        productToUpdate.setCategory("Category3");
        productToUpdate.setStock(3);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(productToUpdate);

        Product productUpdated = productService.updateProduct(1L, productToUpdate);


        assertEquals(1L, productUpdated.getIdProduct());
        assertEquals("Name3", productUpdated.getName());
        assertEquals("Description3", productUpdated.getDescription());
    }

    @Test
    public void testUpdateNotFound() {
        Product productToUpdate = new Product();
        productToUpdate.setIdProduct(3L);
        productToUpdate.setName("Name3");
        productToUpdate.setDescription("Description3");
        productToUpdate.setPrice(3.0);
        productToUpdate.setCategory("Category3");
        productToUpdate.setStock(3);

        when(productRepository.findById(3L)).thenReturn(Optional.empty());

        Product productUpdated = productService.updateProduct(3L, productToUpdate);

        assertNull(productUpdated);

        verify(productRepository, times(0)).save(any(Product.class));
    }

    @Test
    public void testDeleteProduct() {
        doNothing().when(productRepository).deleteById(1L);
        productService.deleteProduct(1L);
        verify(productRepository, times(1)).deleteById(1L);
    }
}
