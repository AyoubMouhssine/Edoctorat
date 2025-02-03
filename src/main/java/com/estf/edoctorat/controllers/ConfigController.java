package com.estf.edoctorat.controllers;
import com.estf.edoctorat.dto.ConfigDTO;
import com.estf.edoctorat.services.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api")
public class ConfigController {
    @Autowired
    private ConfigService configService;
    @GetMapping("/get-base-config")
    public ResponseEntity<ConfigDTO> getConfig(){
        ConfigDTO configDTO = configService.getConfig();
        return configDTO != null ? ResponseEntity.ok(configDTO) : ResponseEntity.notFound().build();
    }
}