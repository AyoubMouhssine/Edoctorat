package com.estf.edoctorat.dtos;

import com.estf.edoctorat.models.Group;
import com.estf.edoctorat.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class Mapper {
    public UserDTO toDTO(User user){
        if(user == null){
            return null;
        }
        List<String> groups = user.getGroups().stream().map(Group::getName).toList();
        UserDTO userDTO =  new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setGroups(groups);
        return userDTO;
    }


    public User toUser(UserCreationDTO userCreationDTO) {
        if (userCreationDTO == null) {
            return null;
        }
        User user = new User();
        user.setUsername(userCreationDTO.getUsername());
        user.setFirstName(userCreationDTO.getFirstName());
        user.setLastName(userCreationDTO.getLastName());
        user.setEmail(userCreationDTO.getEmail());
        user.setPassword(userCreationDTO.getPassword());
        user.setGroups(new ArrayList<>());
        return user;
    }

}
