package com.brainyit.rest.apirest.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.brainyit.rest.apirest.dto.v1.UploadFileResponseDTO;
import com.brainyit.rest.apirest.service.FileStorageService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/file/v1")
public class FileStorageController {

    private FileStorageService service;

    private static final Logger logger = LoggerFactory.getLogger(FileStorageController.class);
    public FileStorageController(FileStorageService service) {
        this.service = service;
    }

    public UploadFileResponseDTO uploadFile(MultipartFile file) {
        String fileName = service.storeFile(file);
        String fileDownloadUri = "/files/" + fileName; // Assuming a base URL for file access
        String fileType = file.getContentType();
        long size = file.getSize();
        logger.info("File uploaded successfully: {}", fileName);
        return new UploadFileResponseDTO(fileName, fileDownloadUri, fileType, size);
    }

    public List<UploadFileResponseDTO> uploadMultipleFiles(List<MultipartFile> files) {
        List<UploadFileResponseDTO> responses = new ArrayList<>();
        for (MultipartFile file : files) {
            responses.add(uploadFile(file));
        }
        return responses;
    }

    public ResponseEntity<ResponseEntity<?>> downloadFile(String fileName, HttpServletRequest request) {


        return null; // Placeholder for actual implementation
    }
}
