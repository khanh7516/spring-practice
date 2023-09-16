package com.example.imagemanagement.repository;

import com.example.imagemanagement.entity.FileServer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileServer, Integer> {
}
