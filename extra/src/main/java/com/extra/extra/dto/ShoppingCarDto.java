package com.extra.extra.dto;

import com.extra.extra.model.Product;
import com.extra.extra.model.ShoppingCar;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShoppingCarDto {
    Long id;
    Double total;
    @Size(min = 1, message = "Debes agregar al menos un producto")
    List<Product> productos;

    public ShoppingCar build(){
        return new ShoppingCar(id, 0.0, productos);
    }
}
