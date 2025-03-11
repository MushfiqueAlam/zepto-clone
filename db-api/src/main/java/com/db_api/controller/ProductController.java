package com.db_api.controller;

import com.db_api.models.Product;
import com.db_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/db/product")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
       Product savedProduct= productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Product> getById(@PathVariable UUID id) {
        // Use findById instead of getById to avoid exceptions
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.get()); // Return 200 OK with the product
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Return 404 Not Found
        }
    }
}
