package com.example.demo.service.impl;

import com.example.demo.model.user.Photo;
import com.example.demo.repository.PhotoRepository;
import com.example.demo.service.PhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@Slf4j
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;

    private Path fileStorageLocation = null;

    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.fileStorageLocation = Paths.get("/home/stas/photos").toAbsolutePath().normalize();
        this.photoRepository = photoRepository;
    }

    @Override
    public void save(Photo photo) {
        photoRepository.save(photo);
        log.info("IN photo save: {}", photo);
    }

    @Override
    public Photo findByid(Long id) {
        Photo result = photoRepository.findById(id).get();
        log.info("IN photo findByid: {}", result);
        return result;

    }

    @Override
    public Resource loadFileAsResource(String fileName) throws MalformedURLException, FileNotFoundException {
        Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
        Resource resource = new UrlResource(filePath.toUri());
        //log.info(filePath.toString());
        //log.info(resource.toString());

        if (resource.exists()) {
            return resource;
        } else {
            throw new FileNotFoundException("File not found " + fileName);
        }
    }

}