package com.extra.extra.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String codigo;
    @Column(length = 30, nullable = false)
    String nombre;
    @Column(precision = 2, nullable = false)
    Double precio;
    @Column(length = 300)
    String descripcion;
    @JsonIgnore
@ManyToMany(mappedBy = "products")
    List<ShoppingCar> shoppingCars;
}
