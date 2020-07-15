package com.example.webstroitelspringboot.controller;

import com.example.webstroitelspringboot.service.YandexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;

@Controller
public class RestS3Controller {
    @Autowired
    private YandexService yandexService;

    @GetMapping("/storage")
    public String storage() {
        return "storage";
    }
    @PostMapping("/storage")
    public String uploadFile(@RequestParam(value = "file") MultipartFile file) {
        this.yandexService.uploadFile(file);
        return "redirect:/upload";
    }

    @GetMapping("/upload")
    public String uploadStatus() {
        return "upload";
    }

}
