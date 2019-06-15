package com.idalia.cosmeticsShop.model;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class imageUpload {
    public static String saveImage(MultipartFile imageFile) throws Exception{
        String folder = "E:/spring-projects/cosmaticphotos/";
        byte[] bytes = imageFile.getBytes();
        Path path = Paths.get(folder + imageFile.getOriginalFilename());
        if (!imageFile.isEmpty()) {
            Files.write(path, bytes);
            return imageFile.getOriginalFilename();
        }
        return "noimage";


    }
}
