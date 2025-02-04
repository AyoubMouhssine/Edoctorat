package com.estf.edoctorat.dto;
import lombok.Data;

import java.util.Date;

@Data
public class FormationDoctoraleDTO {
    private Long id;
    private String pathImage;
    private String initiale;
    private String titre;
    private String axeDeRecherche;
    private Date dateAccreditation;
    private Long cedId;
    private Long etablissementId;
}