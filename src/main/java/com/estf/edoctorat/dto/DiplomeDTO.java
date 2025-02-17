package com.estf.edoctorat.dto;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class DiplomeDTO {
    private Long id;
    private String intitule;
    private String type;
    private Date dateCommission;
    private String mention;
    private String pays;
    private String etablissement;
    private String specialite;
    private String ville;
    private String province;
    private double moyen_generale;
    private List<AnnexeDTO> annexes;
}