package com.estf.edoctorat.dto;
import lombok.Data;
import java.sql.Time;
import java.util.Date;
@Data
public class CommissionDTO {
    private Long id;
    private Date dateCommission;
    private String lieu;
    private Time heure;
    private Long laboratoireId;
}