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
}
