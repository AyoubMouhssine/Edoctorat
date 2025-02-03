package com.estf.edoctorat.dtos.auth;

import lombok.Data;

import java.util.Date;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private String nom;
    private String prenom;
    private String cne;
    private String cin;
    private String nomCandidatAr;
    private String prenomCandidatAr;
    private String adresse;
    private String sexe;
    private String villeDeNaissance;
    private String villeDeNaissanceAr;
    private String ville;
    private Date dateDeNaissance;
    private String typeDeHandiCape;
    private String situationfamiliale;
    private String pays;
    private String telCandidat;
}
