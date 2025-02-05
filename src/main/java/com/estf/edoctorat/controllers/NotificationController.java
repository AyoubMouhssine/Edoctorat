package com.estf.edoctorat.controllers;
import com.estf.edoctorat.config.CustomUserDetails;
import com.estf.edoctorat.dto.NotificationDTO;
import com.estf.edoctorat.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/get-candidat-notifications/")
    public ResponseEntity<?> getNotifications() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body("User not authenticated");
        }
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }
    @GetMapping("/get-candidat-notifications/{id}")
    public ResponseEntity<NotificationDTO> getNotificationById(@PathVariable Long id) {
        NotificationDTO notificationDTO = notificationService.getNotification(id);
        return notificationDTO != null ? ResponseEntity.ok(notificationDTO) : ResponseEntity.notFound().build();
    }
    @PostMapping("/get-candidat-notifications")
    public ResponseEntity<NotificationDTO> createNotification(@RequestBody NotificationDTO notificationDTO) {
        NotificationDTO createdNotificationDTO = notificationService.createNotification(notificationDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body(createdNotificationDTO);
    }

}