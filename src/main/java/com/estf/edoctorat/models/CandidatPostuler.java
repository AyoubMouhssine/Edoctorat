package com.estf.edoctorat.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "candidat_sujet")
public class CandidatPostuler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pathFile;
    @ManyToOne
    @JoinColumn(name = "candidat_id")
    private Candidat Candidat;
    @ManyToOne
    @JoinColumn(name = "sujet_id")
    private Sujet sujet;
}
