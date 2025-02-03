package com.estf.edoctorat.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "ced")
public class Ced {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "LONGTEXT")
    private String description;

    private String pathImage;
    private String initial;

    @Column(nullable = false)
    private String titre;

    @OneToMany(mappedBy = "ced", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FormationDoctorale> formationDoctorales;

    @OneToOne
    @JoinColumn(name = "directeur_id", nullable = false)
    private Professeur professeur;
}


