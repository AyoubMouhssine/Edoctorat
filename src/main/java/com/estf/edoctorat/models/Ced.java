package com.estf.edoctorat.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Ced {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String pathImage;
    private String initial;
    private String titre;
    @OneToOne
    @JoinColumn(name = "directeur_id")
    private Professeur professeur;
}
