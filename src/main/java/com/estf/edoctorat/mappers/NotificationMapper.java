package com.estf.edoctorat.mappers;
import com.estf.edoctorat.dto.NotificationDTO;
import com.estf.edoctorat.models.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
@Mapper
public interface NotificationMapper {
    NotificationMapper INSTANCE = Mappers.getMapper(NotificationMapper.class);
    @Mapping(source = "candidat.id", target = "candidatId")
    @Mapping(source = "commission.id", target = "commissionId")
    @Mapping(source = "sujet.id", target = "sujetId")
    NotificationDTO notificationToNotificationDTO(Notification notification);

    @Mapping(source = "candidatId", target = "candidat.id")
    @Mapping(source = "commissionId", target = "commission.id")
    @Mapping(source = "sujetId", target = "sujet.id")
    Notification notificationDTOToNotification(NotificationDTO notificationDTO);
}