package com.estf.edoctorat.dto;
import lombok.Data;
import java.sql.Date;
@Data
public class DiplomeDTO {
    private Long id;
    private String intitule;
    private String type;
    private Date dateCommission;
    private String mentien;
    private String pays;
    private String etablissement;
    private String specialite;
    private String ville;
    private String province;
    private double moyenGenerale;
    private Long candidatId;

}