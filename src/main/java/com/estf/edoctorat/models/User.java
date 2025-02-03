package com.estf.edoctorat.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.sql.Date;
import java.util.*;

@Entity
@Data
@Table(name = "users")
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
    @OneToOne(mappedBy = "user")
    private Professeur professeur;
    @OneToOne(mappedBy = "user")
    private Candidat candidat;
    @ManyToOne
    @JoinTable(name = "user_group_id")
    private UserGroup userGroup;
}
