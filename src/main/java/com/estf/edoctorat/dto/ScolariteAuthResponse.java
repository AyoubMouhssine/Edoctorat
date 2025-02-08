package com.estf.edoctorat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ScolariteAuthResponse {
        private String access;
        private String refresh;
        private String username;
        private String email;
        private List<String> groups;
}
