package com.extra.extra.service;

import com.extra.extra.dto.ProductDto;
import com.extra.extra.model.Product;
import com.extra.extra.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Product createProduct(ProductDto productDto){
        return productRepository.save(productDto.build());
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

    public Optional<Product> getProductByCode(String code){
        return productRepository.findByCodigo(code);
    }

    public Product updateProduct(ProductDto product){
        if(product.getId() == null){
            throw new RuntimeException("El producto no tiene id");
        }
        Optional<Product> productFound = productRepository.findById(product.getId());
        if(productFound.isEmpty()){
            throw new RuntimeException("El producto no existe");
        }
        return productRepository.save(product.build());
    }

    public void deleteProduct(Long id){
        Optional<Product> productFound = productRepository.findById(id);
        if(productFound.isEmpty()){
            throw new RuntimeException("El producto no existe");
        }
        productRepository.deleteById(id);
    }
}
