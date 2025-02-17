package com.estf.edoctorat.dto;


import lombok.Data;

@Data
public class UpdateCandidatRequest {
    private String nom;
    private String prenom;
    private String nomCandidatAr;
    private String prenomCandidatAr;
    private String cin;
    private String cne;
    private String adresse;
    private String adresseAr;
    private String pays;
    private String sexe;
    private String villeDeNaissance;
    private String villeDeNaissanceAr;
    private String ville;
    private String dateDeNaissance;
    private String typeDeHandiCape;
    private String academie;
    private String mailCandidat;
    private String telCandidat;
    private String pathPhoto;
    private String situation_familiale;
    private String fonctionnaire;
}
