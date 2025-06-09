package com.brainyit.rest.apirest.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;

public interface FileStorageService {
    String storeFile(@NotNull MultipartFile file);

    Resource loadFileAsResource(String fileName);
}
