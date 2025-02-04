package com.estf.edoctorat.dto;

import lombok.Data;

@Data
public class ProfesseurLaboratoireDTO {
    private Long id;
    private String nomLaboratoire;
    private String description;
    private String pathImage;
    private String initial;
    private Long ced_id;
    private Long directeur_id;
    private Long etablissement_id;
}