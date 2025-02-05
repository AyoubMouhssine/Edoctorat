package com.estf.edoctorat.services;
import com.estf.edoctorat.dto.NotificationDTO;
import com.estf.edoctorat.mappers.NotificationMapper;
import com.estf.edoctorat.models.Notification;
import com.estf.edoctorat.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    private final NotificationMapper notificationMapper = NotificationMapper.INSTANCE;


    public List<NotificationDTO> getAllNotifications() {
        List<Notification> notifications = notificationRepository.findAll();
        return notifications.stream()
                .map(NotificationMapper.INSTANCE::notificationToNotificationDTO)
                .collect(Collectors.toList());
    }

    public NotificationDTO getNotification(Long id) {
        Optional<Notification> notification =  notificationRepository.findById(id);
        return notification.map(notificationMapper::notificationToNotificationDTO).orElse(null);
    }

    public NotificationDTO createNotification(NotificationDTO notificationDTO){
        Notification notification = notificationMapper.notificationDTOToNotification(notificationDTO);
        return   notificationMapper.notificationToNotificationDTO(notificationRepository.save(notification));

    }

}