package com.estf.edoctorat.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Commission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateCommission;
    private String lieu;
    private Time heure;
    @ManyToOne
    @JoinColumn(name = "laboratoire_id", referencedColumnName = "id", nullable = false)
    private Laboratoire laboratoire;
    @ManyToMany
    @JoinTable(
            name = "commission_professeurs",
            joinColumns = @JoinColumn(name = "commission_id"),
            inverseJoinColumns = @JoinColumn(name = "professeur_id")
    )
    private List<Professeur> professeurs;

}
