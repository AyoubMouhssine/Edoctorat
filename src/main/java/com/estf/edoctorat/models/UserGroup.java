package com.estf.edoctorat.models;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table (name = "user_groups")
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
