package com.estf.edoctorat.dto;

import lombok.Data;

@Data
public class PaysDTO {
    private Long id;
    private String nom;


    // Add this constructor
    public PaysDTO(String nom) {
        this.nom = nom;
    }

    // Keep the no-argument constructor (required for deserialization)
    public PaysDTO() {
    }
}
