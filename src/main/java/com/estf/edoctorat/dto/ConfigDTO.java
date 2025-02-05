package com.estf.edoctorat.dto;

import lombok.Data;

import java.util.Date;


@Data
public class ConfigDTO {
    private int maxSujetPostuler;
    private Date dateDebutPostulerSujetCandidat;
    private Date dateDebutModifierSujetProf;
    private Date dateFinPostulerSujetCandidat;
    private Date dateFinModifierSujetProf;
}
