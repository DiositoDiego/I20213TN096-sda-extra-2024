package com.extra.extra.controller;

import com.extra.extra.model.ShoppingCar;
import com.extra.extra.service.ShoppingCarService;
import com.extra.extra.utils.Misc;
import com.extra.extra.utils.ResponseApi;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/shopping-car")
public class ShoppingCarController {
    @Autowired
    ShoppingCarService shoppingCarService;

    @PostMapping
    public ResponseApi<ShoppingCar> createShoppingCar(@RequestBody Map<String, Object> map) {
        ResponseApi<ShoppingCar> response = new ResponseApi<>();
        try {
            response.setData(shoppingCarService.createShoppingCar(map));
            response.setStatus(200);
            response.setMessage("success");
        } catch (ConstraintViolationException e) {
            response.setData(null);
            response.setStatus(400);
            response.setMessage("BAD_REQUEST: " + Misc.getErrors(e.getConstraintViolations()));
        } catch (Exception e) {
            response.setData(null);
            response.setStatus(500);
            response.setMessage("INTERNAL_SERVER_ERROR: " + e.getMessage());
        }
        return response;
    }

    @GetMapping
    public ResponseApi<List<ShoppingCar>> getAllShoppingCars(){
        ResponseApi<List<ShoppingCar>> response = new ResponseApi<>();
        try {
            response.setData(shoppingCarService.getAllShoppingCars());
            response.setMessage("success");
            response.setStatus(200);
        } catch (Exception e) {
            response.setData(null);
            response.setMessage("INTERNAL_SERVER_ERROR: " + e.getMessage());
            response.setStatus(500);
        }
        return response;
    }

    @GetMapping("/{id}")
    public ResponseApi<ShoppingCar> getCarById(@PathVariable Long id){
        ResponseApi<ShoppingCar> response = new ResponseApi<>();
        try {
            response.setData(shoppingCarService.getShoppingCarById(id).get());
            response.setMessage("success");
            response.setStatus(200);
        } catch (Exception e) {
            response.setData(null);
            response.setMessage("INTERNAL_SERVER_ERROR: " + e.getMessage());
            response.setStatus(500);
        }
        return response;
    }

    @PutMapping("/add")
    public ResponseApi<ShoppingCar> addProductsToShoppingCar(@RequestBody ShoppingCar shoppingCar){
        ResponseApi<ShoppingCar> response = new ResponseApi<>();
        try {
            response.setData(shoppingCarService.addProductsToShoppingCar(shoppingCar));
            response.setMessage("success");
            response.setStatus(200);
        } catch (ConstraintViolationException e) {
            response.setData(null);
            response.setStatus(400);
            response.setMessage("BAD_REQUEST: " + Misc.getErrors(e.getConstraintViolations()));
        } catch (Exception e) {
            response.setData(null);
            response.setMessage("INTERNAL_SERVER_ERROR: " + e.getMessage());
            response.setStatus(500);
        }
        return response;
    }

    @PutMapping("/remove")
    public ResponseApi<ShoppingCar> removeProductsFromShoppingCar(@RequestBody ShoppingCar shoppingCar){
        ResponseApi<ShoppingCar> response = new ResponseApi<>();
        try {
            response.setData(shoppingCarService.removeProductsFromShoppingCar(shoppingCar));
            response.setMessage("success");
            response.setStatus(200);
        } catch (ConstraintViolationException e) {
            response.setData(null);
            response.setStatus(400);
            response.setMessage("BAD_REQUEST: " + Misc.getErrors(e.getConstraintViolations()));
        } catch (Exception e) {
            response.setData(null);
            response.setMessage("INTERNAL_SERVER_ERROR: " + e.getMessage());
            response.setStatus(500);
        }
        return response;
    }

}
