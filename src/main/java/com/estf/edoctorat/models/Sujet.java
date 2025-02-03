package com.estf.edoctorat.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "sujets")
public class Sujet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;
    private boolean publier;
    @OneToOne
    @JoinColumn(name = "codirecteur_id")
    private Professeur codirecteur;
    @ManyToOne
    @JoinColumn(name = "formationDoctorale_id")
    private FormationDoctorale formationDoctorale;
    @OneToMany(mappedBy = "sujet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CandidatPostuler> candidatPostulers;
    @OneToOne
    @JoinColumn(name = "professeur_id")
    private Professeur professeur;
    @OneToMany(mappedBy = "sujet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications;
}
