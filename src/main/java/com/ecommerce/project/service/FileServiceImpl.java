package com.ecommerce.project.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        //File Names of current / original file
        String originalFileName = file.getOriginalFilename();

        //Generate a unique file name
        // Generate a unique file name
        String randomId = UUID.randomUUID().toString();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf('.'));
        String fileName = randomId.concat(fileExtension);

        // Create the file path
        Path filePath = Paths.get(path, fileName);

        // Ensure the directory exists
        Files.createDirectories(filePath.getParent());

        // Upload the file to the server
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        //returning file name
        return  fileName;

    }
}
