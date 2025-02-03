package com.estf.edoctorat.dtos.auth;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginScolariteResponse {
    private String access;
    private String refresh;
}
