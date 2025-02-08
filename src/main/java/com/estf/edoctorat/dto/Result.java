package com.estf.edoctorat.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class Result<T> {
    private List<T> results;
    private int total;
}
