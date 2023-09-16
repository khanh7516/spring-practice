package com.example.imagemanagement.repository;

import com.example.imagemanagement.entity.FileServer;
import com.example.imagemanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
