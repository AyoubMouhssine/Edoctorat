package com.estf.edoctorat.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Annexe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typeAnnexe;
    private String titre;
    private String pathFile;
    @ManyToOne
    @JoinColumn(name = "diplome_id")
    private Diplome diplome;
}
