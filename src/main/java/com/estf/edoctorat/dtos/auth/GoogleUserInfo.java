package com.estf.edoctorat.dtos.auth;

import lombok.Data;

@Data
public class GoogleUserInfo {
    private String email;
    private String name;
    private String givenName;
    private String familyName;
    private String picture;
}