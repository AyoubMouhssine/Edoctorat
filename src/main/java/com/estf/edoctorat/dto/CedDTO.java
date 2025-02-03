package com.estf.edoctorat.dto;
import lombok.Data;
@Data
public class CedDTO {
    private Long id;
    private String description;
    private String pathImage;
    private String initial;
    private String titre;
    private Long directeurId;
}