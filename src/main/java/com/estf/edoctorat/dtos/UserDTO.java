package com.estf.edoctorat.dtos;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private List<String> groups;
}
