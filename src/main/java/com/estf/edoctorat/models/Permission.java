package com.estf.edoctorat.models;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String codename;

    @ManyToMany(mappedBy = "permissions")
    private List<Group> groups;

    @ManyToMany(mappedBy = "permissions")
    private List<User> users;
}
