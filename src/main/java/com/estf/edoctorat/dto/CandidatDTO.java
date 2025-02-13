package com.estf.edoctorat.dto;

import com.estf.edoctorat.dto.PaysDTO;
import lombok.Data;

import java.util.Date;

@Data
public class CandidatDTO {
    private Long id;
    private String cne;
    private String cin;
    private String nom;
    private String prenom;
    private String nomCandidatAr;
    private String prenomCandidatAr;
    private String adresse;
    private String adresseAr;
    private String sexe;
    private String villeDeNaissance;
    private String villeDeNaissanceAr;
    private String ville;
    private Date dateDeNaissance;
    private String typeDeHandiCape;
    private String academie;
    private String telCandidat;
    private String pathCv;
    private String pathPhoto;
    private int etatDossier;
    private String situationFamiliale; // Matches frontend's "situation_familiale"
    private boolean fonctionnaire;    // Matches frontend's "fonctionnaire"
    private PaysDTO pays;
    private Long userId;
    private String email;     // From User
}