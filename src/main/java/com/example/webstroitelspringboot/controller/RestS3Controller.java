package com.example.webstroitelspringboot.controller;

import com.example.webstroitelspringboot.service.YandexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/storage/")
public class RestS3Controller {

    private YandexService yandexService;

    @Autowired
    public RestS3Controller(YandexService yandexService) {
        this.yandexService = yandexService;
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
        this.yandexService.uploadFile(file);
        return "redirect:/uploadFile";
    }

    @GetMapping("/uploadFile")
    public String uploadFile() {
        return "uploadFile";
    }

}
