package com.example.demo.rest;

import com.example.demo.model.user.Photo;
import com.example.demo.model.user.User;
import com.example.demo.service.AuthenticationFacadeService;
import com.example.demo.service.PhotoService;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.MalformedURLException;
import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/photo/")
public class PhotoRestControllerV1 {

    private final UserService userService;
    private final PhotoService photoService;

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private AuthenticationFacadeService authenticationFacadeService;

    private static final Logger log = LoggerFactory.getLogger(UserRestControllerV1.class);

    @Autowired
    public PhotoRestControllerV1(UserService userService,
                                 PhotoService photoService) {
        this.userService = userService;
        this.photoService = photoService;
    }

    @PostMapping(value = "upload")
    public ResponseEntity uploadImage(@RequestParam("image") MultipartFile image, Principal principal) throws IOException {
        log.info(String.format("received request to photo upload _ by %s", authenticationFacadeService.getAuthentication().getPrincipal()));

        User resultUser = userService.findByUsername(principal.getName());

        if (resultUser == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        if (image != null && !image.getOriginalFilename().isEmpty()) {

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + image.getOriginalFilename();

            byte[] bytes = image.getBytes();
            BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(uploadPath + "/" + resultFileName)));
            stream.write(bytes);
            stream.close();

            String fileDownloadUri = "/download/" + resultFileName; // ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/photo/download/").path(resultFileName).toUriString();

            Photo photo = new Photo();
            photo.setFilename(fileDownloadUri);
            photo.setUserId(resultUser.getId());

            photoService.save(photo);

            return new ResponseEntity(photo, HttpStatus.OK);
        }

        return new ResponseEntity(resultUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/download/{fileName:.+}", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws MalformedURLException, FileNotFoundException {
        log.info(String.format("received request to photo download _ by %s", authenticationFacadeService.getAuthentication().getPrincipal()));

        Resource resource = photoService.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        String.format("attachment; filename=\"%s\"", resource.getFilename()))
                .body(resource);
    }
}