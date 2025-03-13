package com.central_api.controller;

import com.central_api.exception.UnAuthorized;
import com.central_api.exception.UserNotFoundException;
import com.central_api.models.Product;
import com.central_api.requestDto.RegisterProductDto;
import com.central_api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/central/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<?> addProduct(@RequestBody RegisterProductDto productDto, @RequestParam String email){
        try{
            Product product=productService.createProduct(productDto,email);
            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch (UnAuthorized e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
