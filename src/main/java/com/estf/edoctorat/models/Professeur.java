package com.estf.edoctorat.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
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
    @ManyToOne
    @JoinColumn(name = "etablissement_id")
    private Etablissement etablissement;
    @ManyToOne
    @JoinColumn(name = "labo_id", referencedColumnName = "id", nullable = false)
    private Laboratoire laboratoire;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany(mappedBy = "professeurs")
    private List<Commission> commissions;
}
