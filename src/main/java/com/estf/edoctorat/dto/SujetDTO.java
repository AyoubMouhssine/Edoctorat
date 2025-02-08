package com.estf.edoctorat.dto;

import lombok.Data;

@Data
public class SujetDTO {
    private Long id;
    private String titre;
    private String description;
    private boolean publier;
    private ProfesseurDTO professeur;
    private ProfesseurDTO coDirecteur;
    private FormationDoctoraleDTO formationDoctorale;
}