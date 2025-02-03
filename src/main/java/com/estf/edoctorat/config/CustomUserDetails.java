package com.estf.edoctorat.config;

import java.util.ArrayList;
import java.util.Collection;

import com.estf.edoctorat.models.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {
    @Getter
    private final User user;
    private final Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(User user, Collection<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities != null ? authorities : new ArrayList<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getIsActive();
    }

    public boolean isStaff(){
        return user.isStaff();
    }

}