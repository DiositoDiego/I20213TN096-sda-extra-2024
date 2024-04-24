package com.extra.extra.controller;

import com.extra.extra.dto.ProductDto;
import com.extra.extra.model.Product;
import com.extra.extra.service.ProductService;
import com.extra.extra.utils.Misc;
import com.extra.extra.utils.ResponseApi;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseApi<Product> createProduct(@RequestBody ProductDto productDto) {
        ResponseApi<Product> response = new ResponseApi<>();
        try {
            response.setData(productService.createProduct(productDto));
            response.setStatus(200);
            response.setMessage("success");
        } catch (ConstraintViolationException e) {
            response.setData(null);
            response.setStatus(400);
            response.setMessage("BAD_REQUEST: " + Misc.getErrors(e.getConstraintViolations()));
        } catch (Exception e){
            response.setData(null);
            response.setStatus(500);
            response.setMessage("INTERNAL_SERVER_ERROR: " + e.getMessage());
        }
        return response;
    }

    @GetMapping
    public ResponseApi<List<Product>> getAllProducts() {
        ResponseApi<List<Product>> response = new ResponseApi<>();
        try {
            response.setData(productService.getAllProducts());
            response.setStatus(200);
            response.setMessage("success");
        } catch (Exception e){
            response.setData(null);
            response.setStatus(500);
            response.setMessage("INTERNAL_SERVER_ERROR: " + e.getMessage());
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseApi<Product> getProductById(@PathVariable Long id) {
        ResponseApi<Product> response = new ResponseApi<>();
        try {
            response.setData(productService.getProductById(id).orElse(null));
            response.setStatus(200);
            response.setMessage("success");
        } catch (Exception e){
            response.setData(null);
            response.setStatus(500);
            response.setMessage("INTERNAL_SERVER_ERROR: " + e.getMessage());
        }
        return response;
    }

    @GetMapping("/code/{code}")
    public ResponseApi<Product> getProductByCode(@PathVariable String code) {
        ResponseApi<Product> response = new ResponseApi<>();
        try {
            response.setData(productService.getProductByCode(code).orElse(null));
            response.setStatus(200);
            response.setMessage("success");
        } catch (Exception e){
            response.setData(null);
            response.setStatus(500);
            response.setMessage("INTERNAL_SERVER_ERROR: " + e.getMessage());
        }
        return response;
    }

    @PutMapping
    public ResponseApi<Product> updateProduct(@RequestBody Product product) {
        ResponseApi<Product> response = new ResponseApi<>();
        try {
            response.setData(productService.updateProduct(product));
            response.setStatus(200);
            response.setMessage("success");
        } catch (ConstraintViolationException e) {
            response.setData(null);
            response.setStatus(400);
            response.setMessage("BAD_REQUEST: " + Misc.getErrors(e.getConstraintViolations()));
        } catch (Exception e){
            response.setData(null);
            response.setStatus(500);
            response.setMessage("INTERNAL_SERVER_ERROR: " + e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseApi<String> deleteProduct(@PathVariable Long id) {
        ResponseApi<String> response = new ResponseApi<>();
        try {
            productService.deleteProduct(id);
            response.setData("success");
            response.setStatus(200);
            response.setMessage("success");
        } catch (Exception e){
            response.setData(null);
            response.setStatus(500);
            response.setMessage("INTERNAL_SERVER_ERROR: " + e.getMessage());
        }
        return response;
    }
}
