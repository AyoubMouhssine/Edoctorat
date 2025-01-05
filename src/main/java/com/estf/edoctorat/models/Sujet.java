package com.estf.edoctorat.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Sujet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private boolean publier;
    @OneToOne
    @JoinColumn(name = "codirecteur_id")
    private Professeur coDirecteur;
    @ManyToOne
    @JoinColumn(name = "formationDoctorale_id")
    private FormationDoctorale formationDoctorale;
    @ManyToOne
    @JoinColumn(name = "professeur_id")
    private Professeur professeur;

    @ManyToMany
    @JoinTable(
            name = "candidat_sujet",
            joinColumns = @JoinColumn(name = "sujet_id"),
            inverseJoinColumns = @JoinColumn(name = "candidat_id")
    )
    private List<Candidat> candidats;
}
