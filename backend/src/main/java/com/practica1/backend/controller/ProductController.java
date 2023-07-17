package com.practica1.backend.controller;

import com.practica1.backend.exception.HandledErrorResponse;
import com.practica1.backend.model.entity.Customer;
import com.practica1.backend.model.entity.Product;
import com.practica1.backend.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Product", description = "Product API")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products found", content = @Content(schema = @Schema(implementation = Customer.class))),
            @ApiResponse(responseCode = "404", description = "Products not found", content = @Content(schema = @Schema(implementation = HandledErrorResponse.class)))
    })
    @Operation(summary = "Get all Products")
    public Iterable<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found", content = @Content(schema = @Schema(implementation = Customer.class))),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content(schema = @Schema(implementation = HandledErrorResponse.class)))
    })
    @Operation(summary = "Get a Product by id")
    public Product getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @PostMapping("/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created", content = @Content(schema = @Schema(implementation = Customer.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Product data", content = @Content(schema = @Schema(implementation = HandledErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Product email already exists", content = @Content(schema = @Schema(implementation = HandledErrorResponse.class)))
    })
    @Operation(summary = "Create a new Product")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) {
        Product productSaved = productService.addProduct(product);
        return ResponseEntity.ok(productSaved);
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated", content = @Content(schema = @Schema(implementation = Customer.class))),
            @ApiResponse(responseCode = "400", description = "Invalid Product data", content = @Content(schema = @Schema(implementation = HandledErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content(schema = @Schema(implementation = HandledErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Product name already exists", content = @Content(schema = @Schema(implementation = HandledErrorResponse.class)))
    })
    @Operation(summary = "Update a Product by id")
    public ResponseEntity<Product> updateProduct(@Valid @PathVariable long id, @RequestBody Product product) {
        Product productUpdated = productService.updateProduct(id, product);
        return ResponseEntity.ok(productUpdated);
    }

    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product deleted", content = @Content(schema = @Schema(implementation = Customer.class))),
            @ApiResponse(responseCode = "404", description = "Product not found", content = @Content(schema = @Schema(implementation = HandledErrorResponse.class)))
    })
    @Operation(summary = "Delete a Product by id")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @PutMapping("/images")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Add image to product")
    public void addImage() {
        productService.addImage();
    }


}
