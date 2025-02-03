package com.estf.edoctorat.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "notifications")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    @ManyToOne
    @JoinColumn(name = "candidat_id", nullable = false)
    private Candidat candidat;
    @ManyToOne
    @JoinColumn(name="commission_id", nullable = false)
    private Commission commission;
    @ManyToOne
    @JoinColumn(name = "sujet_id", nullable = false)
    private Sujet sujet;
}
