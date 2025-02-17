package com.estf.edoctorat.dto;
import lombok.Data;

import java.util.Date;

@Data
public class FormationDoctoraleDTO {
    private Long id;
    private CedDTO ced;
    private Long etablissement_id;
    private String axeDeRecherche;
    private String pathImage;
    private String titre;
    private String initiale;
    private String dateAccreditation;
}