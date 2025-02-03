package com.estf.edoctorat.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Examiner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String decision ;
    private float noteDossier ;
    private int noteEntretien ;
    private byte publier ;
    private boolean valider;
    @OneToOne
    @JoinColumn(name = "commission_id")
    private Commission commission ;
    @OneToOne
    @JoinColumn(name = "sujet_id")
    private Sujet sujet ;
    @OneToOne
    @JoinColumn(name = "candidat_id")
    private Candidat candidat ;
}
