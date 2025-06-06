package com.brainyit.rest.apirest.service;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;

public interface FileStorageService {
    public String storeFile(@NotNull MultipartFile file);
}
