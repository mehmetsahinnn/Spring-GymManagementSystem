package com.example.gigagym.util;

import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UtilityService {
    public boolean pageExists(String pageName) {
        Path filePath = Paths.get("src/main/resources/templates/" + pageName + ".html");
        return Files.exists(filePath);
    }
}