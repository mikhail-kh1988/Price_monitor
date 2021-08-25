package com.pricemonitor.service;

import com.pricemonitor.entity.Profile;
import com.pricemonitor.entity.Role;
import com.pricemonitor.entity.User;
import com.pricemonitor.repositories.IProfileRepository;
import com.pricemonitor.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IProfileRepository profileRepository;

    public void createNewProfile(String fullName, String login, String password, String email){
        Profile profile = new Profile();

        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);

        profile.setFullName(fullName);
        profile.setUser(user);

        profileRepository.createProfile(profile);
    }

    public void updateProfile(Profile profile){
        profileRepository.updateProfile(profile);
    }

    public void addRoleForUser(Profile profile, Role role){
        Profile findProfile = profileRepository.findProfileById(profile.getId());
        User user = userRepository.findUserById(profile.getUser().getId());

        java.util.List<Role> roles = user.getRoles();
        roles.add(role);
        user.setRoles(roles);

        userRepository.updateUser(user);
    }

    public java.util.List<Profile> getAllProfiles(){
        return profileRepository.getAllProfiles();
    }

    public Profile findProfileById(int id){
        return profileRepository.findProfileById(id);
    }

    public Profile findProfileByUserId(int id){
        return profileRepository.findProfileByUserId(id);
    }
}
