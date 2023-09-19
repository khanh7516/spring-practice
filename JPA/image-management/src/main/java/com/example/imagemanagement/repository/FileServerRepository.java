package com.example.imagemanagement.repository;

import com.example.imagemanagement.entity.FileServer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileServerRepository extends JpaRepository<FileServer, Integer> {
    Optional<List<FileServer>> findByUser_IdOrderByCreatedAtDesc(Integer userId);
}