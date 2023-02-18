package com.cloudcomputing.imagerecognizer.webtier.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/")
public class WelcomeController {

    @GetMapping("cc")
    public String welcome() {
        return "Welcome to Cloud Computing Project 1 - Image Recognition Service!";
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam(name = "fileName") MultipartFile multipartFile) {


        return null;
    }


}
