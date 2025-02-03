package com.estf.edoctorat.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "commission_professeurs")
public class CommissionProfesseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "commission_id")
    private Commission commission;
    @ManyToOne
    @JoinColumn(name = "professeur_id")
    private Professeur professeur;
}
