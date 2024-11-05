package com.ozcaar.aromalia.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ozcaar.aromalia.Services.ImageService;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    ImageService imageService;

    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        String fileName = imageService.storeImage(file);
        return ResponseEntity.status(HttpStatus.CREATED).body(fileName);
    }

    @GetMapping("/{imageName}")
    @ResponseBody
    public ResponseEntity<Resource> serveImage(@PathVariable String imageName) {
        Resource file = imageService.loadImage(imageName);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(file);
    }
}