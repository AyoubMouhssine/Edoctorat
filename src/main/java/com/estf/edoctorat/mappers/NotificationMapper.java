package com.estf.edoctorat.mappers;

import com.estf.edoctorat.dto.NotificationDTO;
import com.estf.edoctorat.models.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NotificationMapper {
    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);

    @Mapping(source = "commission", target = "commission")
    @Mapping(source = "sujet", target = "sujet")
    NotificationDTO notificationToNotificationDTO(Notification notification);

    @Mapping(source = "commission", target = "commission")
    @Mapping(source = "sujet", target = "sujet")
    Notification notificationDTOToNotification(NotificationDTO notificationDTO);
}
