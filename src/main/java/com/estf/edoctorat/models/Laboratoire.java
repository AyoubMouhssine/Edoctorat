package com.estf.edoctorat.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Laboratoire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomLaboratoire;
    private String description;
    private String pathImage;
    private String initial;
    @OneToOne
    @JoinColumn(name = "ced_id")
    private Ced ced;
    @OneToOne
    @JoinColumn(name = "professeur_id")
    private Professeur professeur;
    @OneToOne
    @JoinColumn(name = "etablissement_id")
    private Etablissement etablissement;
}
