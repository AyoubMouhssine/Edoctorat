package com.estf.edoctorat.dto;

import com.estf.edoctorat.models.FormationDoctorale;
import com.estf.edoctorat.models.Professeur;
import lombok.Data;

@Data
public class SujetDTO {
    private Long id;
    private ProfesseurDTO professeur;
    private ProfesseurDTO coDirecteur;
    private String titre;
    private String description;
    private FormationDoctoraleDTO formationDoctorale;
    private boolean publier;
}