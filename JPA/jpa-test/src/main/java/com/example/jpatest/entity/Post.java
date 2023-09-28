package com.example.jpatest.entity;

import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post")
public class Post {
    @Id
    private String id;
    private String title;

    @PrePersist
    private void generateId() {
        if (id == null) {
            // Tạo một ID ngẫu nhiên sử dụng UUID
            id = UUID.randomUUID().toString();
        }
    }
}
