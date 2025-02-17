package com.estf.edoctorat.dto;

import lombok.Data;

import java.util.List;

@Data
public class Result<T> {
    private int count;
    private String next;
    private String previous;
    private List<T> results;
}