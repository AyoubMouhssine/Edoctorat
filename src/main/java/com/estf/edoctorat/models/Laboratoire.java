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
    @JoinColumn(name = "ced_id", referencedColumnName = "id", nullable = false)
    private Ced ced;

    @OneToOne
    @JoinColumn(name = "directeur_id", referencedColumnName = "id", nullable = false)
    private Professeur professeur;

    @OneToOne
    @JoinColumn(name = "etablissement_id", referencedColumnName = "idEtablissement", nullable = false)
    private Etablissement etablissement;
}
