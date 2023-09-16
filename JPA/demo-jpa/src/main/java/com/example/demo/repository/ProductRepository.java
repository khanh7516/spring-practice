package com.example.demo.repository;

import com.example.demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Page<Product> findByNameOrderByCountDesc (String name, Pageable pageable);
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    List<Product> findAllByOrderByPriceDesc();

    Page<Product> findAllByOrderByNameAsc(String name, Pageable pageable);

    Page<Product> findAllByOrderByCountAsc(Pageable pageable);

    Page<Product> findProductByBrandOrderByPriceAsc(String brand, Pageable pageable);

    long countAllByBrand(String brand);

    @Query("SELECT SUM(p.count) FROM Product p WHERE p.brand = :brand")
    long getTotalCountByBrand(@Param("brand") String brand);

}
