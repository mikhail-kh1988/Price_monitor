package com.pricemonitor.service;

import com.pricemonitor.dto.ProfileDTO;
import com.pricemonitor.dto.UserPasswordDTO;
import com.pricemonitor.entity.Profile;
import com.pricemonitor.entity.Role;
import com.pricemonitor.entity.User;
import com.pricemonitor.repository.IProfileRepository;
import com.pricemonitor.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IProfileRepository profileRepository;

    @Autowired
    private UserService userService;

    public void createNewProfile(String fullName, String login, String password, String email){
        Profile profile = new Profile();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


        User user = new User();
        user.setLogin(login);
        String passwd = encoder.encode(password);
        user.setPassword(passwd);
        user.setEmail(email);

        profile.setFullName(fullName);
        profile.setUser(user);

        profileRepository.createProfile(profile);
    }

    public void updateProfile(ProfileDTO dto){
        Profile profile = findProfileById(dto.getProfileId());
        profile.setFullName(dto.getFullName());
        profile.setAddress(dto.getAddress());
        profile.setPhone(dto.getPhone());

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

    public void changeUserPassword(UserPasswordDTO dto){
        User currentUser =  userService.findUserByLogin(dto.getLogin());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        String passwd = encoder.encode(dto.getPassword());
        currentUser.setPassword(passwd);
        userService.updateUser(currentUser);
    }
}
