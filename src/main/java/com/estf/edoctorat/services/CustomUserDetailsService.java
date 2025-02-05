package com.estf.edoctorat.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.estf.edoctorat.config.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.estf.edoctorat.models.AuthGroup;
import com.estf.edoctorat.models.User;
import com.estf.edoctorat.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmailWithGroups(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        List<GrantedAuthority> authorities = user.getUserGroups().stream()
                .map(userGroup -> (GrantedAuthority) new SimpleGrantedAuthority(userGroup.getAuthGroup().getName()))
                .collect(Collectors.toList());
        return new CustomUserDetails(user, authorities);
    }
}