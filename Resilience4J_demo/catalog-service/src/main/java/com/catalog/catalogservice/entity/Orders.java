package com.catalog.catalogservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ORDER_TBL")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="category")
    private String category;

    @Column(name = "colour")
    private String colour;

    @Column(name = "price")
    private double price;

    public Orders(String name, String category, String colour, double price) {
        this.name = name;
        this.category = category;
        this.colour = colour;
        this.price = price;
    }
}
