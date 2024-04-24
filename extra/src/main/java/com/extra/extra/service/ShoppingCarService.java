package com.extra.extra.service;

import com.extra.extra.dto.ShoppingCarDto;
import com.extra.extra.model.Product;
import com.extra.extra.model.ShoppingCar;
import com.extra.extra.repository.ProductRepository;
import com.extra.extra.repository.ShoppingCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class ShoppingCarService {
    @Autowired
    ShoppingCarRepository shoppingCarRepository;

    @Autowired
    ProductRepository productRepository;

    public ShoppingCar createShoppingCar(Map<String, Object> map){
        ShoppingCarDto shoppingCarDto = new ShoppingCarDto();
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < (Integer) map.get("cantidad"); i++){
            products.add(productRepository.findByCodigo((String) map.get("codigo")).get());
        }
        shoppingCarDto.setProductos(products);
        Double total = products.get(0).getPrecio() * (Integer) map.get("cantidad");
        for (Product product: shoppingCarDto.getProductos()) {
            if(productRepository.findByCodigo(product.getCodigo()).isEmpty()){
                throw new RuntimeException("Uno o más productos no existen");
            }
            total += product.getPrecio();
        }
        shoppingCarDto.setTotal(total);
        return shoppingCarRepository.save(shoppingCarDto.build());
    }

    public List<ShoppingCar> getAllShoppingCars(){
        return shoppingCarRepository.findAll();
    }

    public Optional<ShoppingCar> getShoppingCarById(Long id){
        return shoppingCarRepository.findById(id);
    }

    public ShoppingCar addProductsToShoppingCar(ShoppingCar shoppingCar){
        if(shoppingCar.getId() == null){
            throw new RuntimeException("El carrito no tiene id");
        }
        Optional<ShoppingCar> shoppingCarFound = shoppingCarRepository.findById(shoppingCar.getId());
        if(shoppingCarFound.isEmpty()){
            throw new RuntimeException("El carrito no existe");
        }
        List<Product> allProducts = new ArrayList<>();
        allProducts.addAll(shoppingCarFound.get().getProducts());
        allProducts.addAll(shoppingCar.getProducts());
        shoppingCar.setProducts(allProducts);
        return shoppingCarRepository.save(shoppingCar);
    }

    public ShoppingCar removeProductsFromShoppingCar(ShoppingCar shoppingCar) {
        if (shoppingCar.getId() == null) {
            throw new RuntimeException("El carrito no tiene id");
        }
        Optional<ShoppingCar> shoppingCarFound = shoppingCarRepository.findById(shoppingCar.getId());
        if (shoppingCarFound.isEmpty()) {
            throw new RuntimeException("El carrito no existe");
        }
        List<Product> allProducts = new ArrayList<>(shoppingCarFound.get().getProducts());
        for (Product product: shoppingCar.getProducts()) {
            if (product != null && shoppingCarFound.get().getProducts().contains(product)){
                allProducts.remove(product);
            }
        }
        if (allProducts.size() == 0){
            shoppingCar.setProducts(new ArrayList<>());
        } else {
            shoppingCar.setProducts(allProducts);
        }
        return shoppingCarRepository.save(shoppingCar);
    }

}
