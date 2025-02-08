package com.estf.edoctorat.services;

import com.estf.edoctorat.models.AuthGroup;
import com.estf.edoctorat.repositories.AuthGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthGroupService {

    @Autowired
    private AuthGroupRepository authGroupRepository;

    public AuthGroup findOrCreateAuthGroup(String groupName) {
        return authGroupRepository.findByName(groupName)
                .orElseGet(() -> {
                    AuthGroup newGroup = new AuthGroup();
                    newGroup.setName(groupName);
                    return authGroupRepository.save(newGroup);
                });
    }
}
