package com.estf.edoctorat.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
@Table(name = "config")
public class Config {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "max_sujet_postuler")
    private int maxSujetPostuler;

    @Column(name = "date_debut_postuler_sujet_candidat")
    private Date dateDebutPostulerSujetCandidat;

    @Column(name = "date_debut_modifier_sujet_prof")
    private Date dateDebutModifierSujetProf;

    @Column(name = "date_fin_postuler_sujet_candidat")
    private Date dateFinPostulerSujetCandidat;

    @Column(name = "date_fin_modifier_sujet_prof")
    private Date dateFinModifierSujetProf;
}
