package com.estf.edoctorat.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "formation_doctorale")
public class FormationDoctorale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pathImage;
    private String initiale;
    @Column(nullable = false)
    private String titre;
    @Column(columnDefinition = "LONGTEXT")
    private String axeDeRecherche;
    @Column(columnDefinition = "DATE")
    private Date dateAccreditation;
    @OneToMany(mappedBy = "formationDoctorale", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sujet> sujets;
    @ManyToOne
    @JoinColumn(name = "ced_id")
    private Ced ced;
    @ManyToOne
    @JoinColumn(name="etablissement_id")
    private Etablissement etablissement;
}
