package com.example.webstroitelspringboot.controller;

import com.example.webstroitelspringboot.service.YandexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

@Controller
public class RestS3Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestS3Controller.class);

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
    public String uploadStatus(Model model) {
        Map<String, String> items = this.yandexService.listFiles();
        model.addAttribute("items", items);

        return "upload";
    }
}
