package com.estf.edoctorat.dto;
import lombok.Data;
@Data
public class ProfesseurDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String cin;
    private String telProfesseur;
    private String pathPhoto;
    private String grade;
    private String numSOM;
    private int nombreEncadrer;
    private int nombreProposer;
    private long labo_id;
    private Long etablissementId;
//    private Long nom;

}