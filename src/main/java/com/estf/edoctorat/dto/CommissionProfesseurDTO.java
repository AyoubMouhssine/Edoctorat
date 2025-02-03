// src/main/java/com/estf/edoctorat/dto/CommissionProfesseurDTO.java
package com.estf.edoctorat.dto;
import lombok.Data;
@Data
public class CommissionProfesseurDTO {
    private Long id;
    private Long commissionId;
    private Long professeurId;
}