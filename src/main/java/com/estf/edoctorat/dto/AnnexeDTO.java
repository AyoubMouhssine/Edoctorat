package com.estf.edoctorat.dto;

import lombok.Data;

@Data
public class AnnexeDTO {
    private Long id;
    private String typeAnnexe;
    private String titre;
    private String pathFile;
    private Long diplomeId;
}