package com.cloudcomputing.imagerecognizer.webtier.controller;

import com.cloudcomputing.imagerecognizer.webtier.service.RecognizerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/")
public class ImageRecognizerController {

    @Autowired
    private RecognizerService recognizerService;

    @PostMapping("upload")
    public ResponseEntity<String> uploadImage(@RequestParam(value = "file") MultipartFile multipartFile) {

        ResponseEntity<String> responseEntity;
        try{
            log.info("received a request to upload a file to s3: {}", multipartFile.getOriginalFilename());
            String fileName = multipartFile.getOriginalFilename() + "_" + System.currentTimeMillis();
            recognizerService.uploadImage(multipartFile, fileName);
            responseEntity = new ResponseEntity<>(fileName, HttpStatus.OK);

        } catch (Exception e) {
            log.error("Unexpected error while uploading image ", e);
            responseEntity = new ResponseEntity<String>("Unexpected Error, please try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return responseEntity;
    }

}
