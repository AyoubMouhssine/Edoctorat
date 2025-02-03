package com.estf.edoctorat.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DirecteurPoleCalendrierDTO {
    private Long id;
    private String action;
    private Date dateDebut;
    private Date dateFin;
    private String pour;
}