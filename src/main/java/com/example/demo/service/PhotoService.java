package com.example.demo.service;

import com.example.demo.model.user.Photo;
import org.springframework.core.io.Resource;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

public interface PhotoService {

    void save(Photo photo);

    Photo findByid(Long id);

    Resource loadFileAsResource(String fileName) throws MalformedURLException, FileNotFoundException;

}
