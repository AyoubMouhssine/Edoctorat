package com.estf.edoctorat.dtos.auth;

import lombok.Data;

@Data
public class EmailConfirmationRequest {
    private String email;
    private String nom;
    private String prenom;
    private String origin;
}
