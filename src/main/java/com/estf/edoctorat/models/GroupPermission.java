package com.estf.edoctorat.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table (name = "group_permissions")
public class GroupPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;
    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    private AuthGroup group;
}
