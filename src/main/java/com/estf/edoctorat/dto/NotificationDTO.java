package com.estf.edoctorat.dto;
import lombok.Data;
@Data
public class NotificationDTO {
    private Long id;
    private String type;
    private Long commissionId;
    private Long sujetId;
}