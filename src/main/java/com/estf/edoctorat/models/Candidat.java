package com.estf.edoctorat.models;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Entity
@Data
public class Candidat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cne;
    private String cin;
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
    private String situationFamiliale;
    private boolean fonctionaire;
    @ManyToOne
    @JoinColumn(name = "pays_id")
    private Pays pays;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany(mappedBy = "candidats")
    private List<Sujet> sujets;
}
