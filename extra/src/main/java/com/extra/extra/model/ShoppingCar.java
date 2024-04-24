package com.extra.extra.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "shopping_car")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ShoppingCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(precision = 2)
    Double total;
    @ManyToMany
    @JoinTable(name = "product_shopping_car", joinColumns = @JoinColumn(name = "id_shopping_car"), inverseJoinColumns = @JoinColumn(name = "id_product"))
    List<Product> products;
}
