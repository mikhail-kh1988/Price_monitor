package com.pricemonitor.service;

import com.pricemonitor.entity.Profile;
import com.pricemonitor.entity.Role;
import com.pricemonitor.entity.User;
import com.pricemonitor.repositories.IProfileRepository;
import com.pricemonitor.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    public void createNewUser(User user){
        userRepository.createUser(user);
    }

    public void updateUser(User user){
        userRepository.updateUser(user);
    }

    public User findUserByLogin(String login){
        return userRepository.findUserByName(login);
    }

    public void addUserRole(String login, String role){
        User tempUser = userRepository.findUserByName(login);

        Role setRole = new Role();
        setRole.setName(role);

        java.util.List<Role> roleList = new ArrayList<>();
        roleList.add(setRole);

        tempUser.setRoles(roleList);
        userRepository.updateUser(tempUser);
    }

}