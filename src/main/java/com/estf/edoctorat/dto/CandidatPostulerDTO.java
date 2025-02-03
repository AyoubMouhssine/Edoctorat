// src/main/java/com/estf/edoctorat/dto/CandidatPostulerDTO.java
package com.estf.edoctorat.dto;
import lombok.Data;
@Data
public class CandidatPostulerDTO {
    private Long id;
    private String pathFile;
    private Long candidatId;
    private Long sujetId;
}