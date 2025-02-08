package com.estf.edoctorat.dtos.auth;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    private String access;
    private String refresh;
    private String username;
    private String email;
    private List<String> groups;
}
