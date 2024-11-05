package com.ozcaar.aromalia.Services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String storeImage(MultipartFile file) {
        try {
            // Verifica si el directorio de uploads existe, si no, lo crea
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // String FileName = file.getOriginalFilename();
            String uniqueFileName = UUID.randomUUID().toString() + ".png";
            Path path = uploadPath.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), path);
            return uniqueFileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Resource loadImage(String imageName) {
        try {
            Path imagePath = Paths.get(uploadDir).resolve(imageName).normalize();
            Resource resource = new UrlResource(imagePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("No se puede leer la imagen: " + imageName);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}