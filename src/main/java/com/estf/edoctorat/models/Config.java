// src/main/java/com/estf/edoctorat/models/Config.java
package com.estf.edoctorat.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Config {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String logo;
    private String email;
    private String  tel;
    private String  adresse;
    private String  adresseAr;
}