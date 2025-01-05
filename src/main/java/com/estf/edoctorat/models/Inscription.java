package com.estf.edoctorat.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateDiposeDossier;
    private String remarque;
    private boolean valider;
    @OneToOne
    @JoinColumn(name = "candidat_id")
    private Candidat candidat;
    @OneToOne
    @JoinColumn(name="sujet_id")
    private Sujet sujet;
}
