package com.estf.edoctorat.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Etablissement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEtablissement;

    private String nomEtablissement;

    @OneToMany
    @JoinColumn(name = "etablissement_id")
    private List<FormationDoctorale> formationDoctorales;
}

/*
package com.estf.edoctorat.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Etablissement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEtablissement;
    private String nomEtablissement;
    @OneToMany
    @JoinColumn(name = "etablissement_id")
    private List<FormationDoctorale> formationDoctorales;
}
*/
