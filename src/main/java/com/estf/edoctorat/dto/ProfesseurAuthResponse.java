package com.estf.edoctorat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class ProfesseurAuthResponse {
    private String email;
    private String nom;
    private String prenom;
    private String pathPhoto;
    private List<String> groups;
    private Map<String, Object> misc;
    private String grade;
    private int nombreProposer;
    private int nombreEncadrer;
    private String access;
    private String refresh;
}
