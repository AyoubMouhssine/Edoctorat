package com.estf.edoctorat.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private Date lastLogin;
    private boolean isSuperuser;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isStaff;
    private boolean isActive;
    private Date dateJoined;
    @ManyToMany
    @JoinTable(
            name="user_groups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name="group_id")
    )
    private List<Group> groups;

    @ManyToMany
    @JoinTable(
            name = "user_permissions",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private List<Permission> permissions;
}
