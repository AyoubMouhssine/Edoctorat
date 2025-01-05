package com.estf.edoctorat.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "commission_professeurs")
public class CommissionProfesseurs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
