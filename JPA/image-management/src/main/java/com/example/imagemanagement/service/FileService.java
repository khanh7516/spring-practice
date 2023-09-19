package com.example.imagemanagement.service;


import com.example.imagemanagement.entity.FileServer;
import com.example.imagemanagement.entity.User;
import com.example.imagemanagement.repository.FileServerRepository;
import com.example.imagemanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Service
public class FileService {
    @Autowired
    private FileServerRepository fileServerRepository;
    @Autowired
    private UserRepository userRepository;

    public FileServer uploadFile(Integer userId, MultipartFile file) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id " + userId));

        FileServer fileServer = new FileServer();
        try {
        fileServer.setType(file.getContentType());
        fileServer.setData(file.getBytes());
        fileServer.setUser(user);
        } catch (IOException e) {
            throw new RuntimeException("Cannot upload file!");
        }
        fileServerRepository.save(fileServer);
        return fileServer;
    }

    public FileServer getFileById(Integer id) {
        return fileServerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("File not found with id " + id));
    }

    public List<FileServer> getFilesByUserId(Integer userId) {
        Optional<List<FileServer>> files = fileServerRepository.findByUser_IdOrderByCreatedAtDesc(userId);

        if (files.isPresent()) {
            return files.get();
        } else {
            throw new RuntimeException("Cannot find user id " + userId);
        }
    }

    public void deleteFile(Integer id) {
        FileServer file = getFileById(id);
        fileServerRepository.delete(file);
    }

}
