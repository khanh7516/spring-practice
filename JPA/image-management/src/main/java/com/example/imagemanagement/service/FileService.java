package com.example.imagemanagement.service;


import com.example.imagemanagement.entity.FileServer;
import com.example.imagemanagement.repository.FileRepository;
import com.example.imagemanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private UserRepository userRepository;
    public FileServer uploadFile(Integer userId, MultipartFile file) {
        return null;
    }

    public FileServer getFileById(Integer id) {
        return null;
    }

    public void deleteFile(Integer id) {
        FileServer file = getFileById(id);

        fileRepository.delete(file);
    }

}
