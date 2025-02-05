package com.estf.edoctorat.controllers;
import com.estf.edoctorat.dto.ConfigDTO;
import com.estf.edoctorat.services.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api")
public class ConfigController {
    @Autowired
    private ConfigService configService;
    @GetMapping("/get-base-config/")
    public ResponseEntity<?> getConfig(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body("User not authenticated");
        }

        ConfigDTO configDTO = configService.getConfigInfo();
        return configDTO != null ? ResponseEntity.ok(configDTO) : ResponseEntity.notFound().build();
    }
}