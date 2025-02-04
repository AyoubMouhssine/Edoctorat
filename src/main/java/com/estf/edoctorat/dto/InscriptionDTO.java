package com.estf.edoctorat.dto;
import lombok.Data;

import java.util.Date;

@Data
public class InscriptionDTO {
    private Long id;
    private Date dateDisposeDossier;
    private String remarque;
    private byte valider;
    private Long candidatId;
    private Long sujetId;
}