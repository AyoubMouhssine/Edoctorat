package com.estf.edoctorat.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${app.upload.dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String candidateDirPath = "file:" + uploadDir + "/candidats/";

        registry.addResourceHandler("/uploads/candidats/**")
                .addResourceLocations(candidateDirPath);
    }
}