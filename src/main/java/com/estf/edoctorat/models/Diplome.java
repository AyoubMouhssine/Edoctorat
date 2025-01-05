package com.estf.edoctorat.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Entity
@Data
public class Diplome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @ManyToOne
    @JoinColumn(name = "candidat_id")
    private Candidat candidat;
}
