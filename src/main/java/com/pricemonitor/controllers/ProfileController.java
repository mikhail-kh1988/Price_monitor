package com.pricemonitor.controllers;

import com.pricemonitor.dto.RegisterProfileDTO;
import com.pricemonitor.dto.UserPasswordDTO;
import com.pricemonitor.dto.UserRoleDTO;
import com.pricemonitor.entity.Profile;
import com.pricemonitor.entity.Role;
import com.pricemonitor.entity.User;
import com.pricemonitor.service.ProfileService;
import com.pricemonitor.service.UserService;
import com.pricemonitor.tools.JSONConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public String getAllProfiles(){
        JSONConverter converter = new JSONConverter(profileService.getAllProfiles());
        return converter.getJSON();
    }

    @PostMapping(path = "/register")
    public String registerProfile(@RequestBody RegisterProfileDTO profileDTO){
        profileService.createNewProfile(profileDTO.getFullName(), profileDTO.getLogin(),
                profileDTO.getPassword(), profileDTO.getEmail());
        int tempID = userService.findUserByLogin(profileDTO.getLogin()).getId();
        return profileService.findProfileByUserId(tempID).toString();
    }

    @PostMapping(path = "/updateProfile")
    public String updateProfile(@RequestBody Profile profile){
        profileService.updateProfile(profile);
        Profile newProfile = profileService.findProfileById(profile.getId());
        JSONConverter converter = new JSONConverter(newProfile);
        return converter.getJSON();
    }

    @PostMapping(path = "/changepassword")
    public String changeUserPassword(@RequestBody UserPasswordDTO userPasswordDTO){
        User currentUser = userService.findUserByLogin(userPasswordDTO.getLogin());
        currentUser.setPassword(userPasswordDTO.getPassword());
        userService.updateUser(currentUser);
        return "success!";
    }

    @PostMapping(path = "/addRole")
    public String addRoleForUser(@RequestBody UserRoleDTO roleDTO){
        userService.addUserRole(roleDTO.getLogin(), roleDTO.getRoleName());
        User user = userService.findUserByLogin(roleDTO.getLogin());
        JSONConverter converter = new JSONConverter(user.getRoles());
        return converter.getJSON();
    }
}
