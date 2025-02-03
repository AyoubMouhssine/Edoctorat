package com.estf.edoctorat.controllers;
import com.estf.edoctorat.dto.NotificationDTO;
import com.estf.edoctorat.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/get-candidat-notifications")
    public ResponseEntity<List<NotificationDTO>> getNotifications(@RequestParam(required = false) Long candidatId) {
        List<NotificationDTO> notificationDTOS;
        if(candidatId == null){
            notificationDTOS = notificationService.getNotifications();
        }else{
            notificationDTOS =  notificationService.getNotificationsByCandidatId(candidatId);
        }
        return ResponseEntity.ok(notificationDTOS);
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