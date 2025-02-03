package com.estf.edoctorat.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

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
    @OneToMany(mappedBy = "diplome", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Annexe> annexes;
    @ManyToOne
    @JoinColumn(name="candidat_id", nullable=false)
    private Candidat candidat;
}
