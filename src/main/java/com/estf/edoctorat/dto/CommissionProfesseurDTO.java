// src/main/java/com/estf/edoctorat/dto/CommissionProfesseurDTO.java
package com.estf.edoctorat.dto;
import lombok.Data;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Data
public class CommissionProfesseurDTO {
    private Long id;
    private Long commissionId;
    private Long professeurId;
    private Date dateCommission;
    private String lieu;
    private Time heure;
    private ProfesseurDTO participants;
//    private List<SujetDTO> sujets;
}