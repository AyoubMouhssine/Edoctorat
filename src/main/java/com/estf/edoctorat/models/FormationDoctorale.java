package com.estf.edoctorat.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class FormationDoctorale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pathImage;
    private String initiale;
    private String titre;
    @Column(columnDefinition = "LONGTEXT")
    private String axeDeRecherche;
    private Date dateAccreditation;
    @ManyToOne
    @JoinColumn(name = "ced_id")
    private Ced ced;
    @ManyToOne
    @JoinColumn(name="etablissement_id")
    private Etablissement etablissement;
}
