package com.estf.edoctorat.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "inscriptions")
public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateDisposeDossier;
    private String remarque;
    private byte valider;
    @OneToOne
    @JoinColumn(name = "candidat_id")
    private Candidat candidat;
    @OneToOne
    @JoinColumn(name="sujet_id")
    private Sujet sujet;
}
