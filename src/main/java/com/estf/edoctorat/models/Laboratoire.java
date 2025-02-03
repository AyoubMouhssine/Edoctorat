package com.estf.edoctorat.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "laboratoires")
public class Laboratoire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomLaboratoire;
    private String description;
    private String pathImage;
    private String initial;

    @OneToOne(mappedBy = "laboratoire", cascade = CascadeType.ALL, orphanRemoval = true)
    private Commission commission;

    @OneToOne
    @JoinColumn(name = "ced_id")
    private Ced ced;

    @OneToOne
    @JoinColumn(name = "directeur_id")
    private Professeur professeur;

    @OneToOne
    @JoinColumn(name = "etablissement_id")
    private Etablissement etablissement;
}