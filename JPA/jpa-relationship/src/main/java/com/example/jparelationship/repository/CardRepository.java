package com.example.jparelationship.repository;

import com.example.jparelationship.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Integer> {
}
