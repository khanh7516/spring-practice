package com.example.demo.entity;

import jakarta.persistence.*;

import lombok.*;


import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name; // tên sản phẩm

    private double price; // giá

    private String brand; // thương hiệu

    private LocalDate createdAt; // ngày tạo

    private Integer count; // số lượng

    public Product(String name, Double price, String brand, LocalDate createdAt, Integer count) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.createdAt = createdAt;
        this.count = count;
    }
}

