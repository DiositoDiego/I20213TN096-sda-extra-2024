package com.extra.extra.dto;

import com.extra.extra.model.Product;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
    Long id;
    @Null
    @Length(min = 4, max = 10, message = "El código debe tener entre 4 y 10 caracteres")
    String codigo;
    @Length(min = 1, max = 30, message = "El nombre debe tener entre 1 y 30 caracteres")
    String nombre;
    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    Double precio;
    @Null
    @Length(min = 1, max = 300, message = "La descripción debe tener entre 1 y 300 caracteres")
    String descripcion;

    public Product build(){
        return new Product(id, codigo, nombre, precio, descripcion, new ArrayList<>());
    }
}
