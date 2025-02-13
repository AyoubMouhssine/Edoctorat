package com.estf.edoctorat.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SujetDTO {
    private Long id;
    private String titre;
    private String description;
    private boolean publier;
    private Long formationDoctoraleId; // Use this field for the formationDoctoraleId
    private ProfesseurDTO professeur;
    private CoDirecteurDTO coDirecteur;
    private FormationDoctoraleDTO formationDoctorale;
}
