package com.estf.edoctorat.dto;
import lombok.Data;
@Data
public class ExaminerDTO {
    private Long id;
    private String decision;
    private float noteDossier;
    private int noteEntretien;
    private byte publier;
    private boolean valider;
    private Long commissionId;
    private Long sujetId;
    private Long candidatId;
}