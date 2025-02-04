package com.estf.edoctorat.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "professeurs")
public class Professeur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cin;
    private String telProfesseur;
    private String pathPhoto;
    private String grade;
    private String numSOM;
    private int nombreEncadrer;
    private int nombreProposer;

    private long labo_id;

    @ManyToOne
    @JoinColumn(name = "etablissement_id")
    private Etablissement etablissement;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "professeur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommissionProfesseur> commissionProfesseurs;

    @OneToOne(mappedBy = "professeur", cascade = CascadeType.ALL, orphanRemoval = true)
    private Ced ced;
}