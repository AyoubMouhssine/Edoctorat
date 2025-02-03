package com.estf.edoctorat.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;


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

    public boolean getIsActive(){
        return this.isActive;
    }

    public void setIsActive(boolean active){
        this.isActive = active;
    }

    public void setIsStuff(boolean stuff) {
        this.isStaff = stuff;
    }
}
