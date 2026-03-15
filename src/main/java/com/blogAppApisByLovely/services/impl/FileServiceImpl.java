package com.blogAppApisByLovely.services.impl;

import com.blogAppApisByLovely.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
       String name = file.getOriginalFilename();

       String randomID = UUID.randomUUID().toString();
       String fileName1= randomID.concat(name.substring(name.lastIndexOf(".")));

       String filePath = path + File.separator + fileName1;

       File f = new File(path);
       if(!f.exists()){
           f.mkdirs();
       }
       Files.copy(file.getInputStream(), Paths.get(filePath));
       return fileName1;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        return null;
    }
}
