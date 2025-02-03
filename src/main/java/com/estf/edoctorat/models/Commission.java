package com.estf.edoctorat.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "commissions")
public class Commission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "DATE", nullable = false)
    private Date dateCommission;
    @Column(nullable = false)
    private String lieu;
    @Column(nullable = false)
    private Time heure;
    @OneToMany(mappedBy = "commission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommissionProfesseur> commissionProfesseurs;
    @OneToOne
    @JoinColumn(name = "labo_id")
    private Laboratoire laboratoire;
    @OneToMany(mappedBy = "commission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Notification> notifications;
}
