package com.estf.edoctorat.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    @ManyToOne
    @JoinColumn(name = "candidat_id")
    private Candidat candidat;
    @ManyToOne
    @JoinColumn(name="commission_id")
    private Commission commission;
    @ManyToOne
    @JoinColumn(name = "sujet_id")
    private Sujet sujet;
}
