package com.pricemonitor.controllers;

import com.pricemonitor.dto.RegisterProfileDTO;
import com.pricemonitor.dto.UserPasswordDTO;
import com.pricemonitor.dto.UserRoleDTO;
import com.pricemonitor.entity.Profile;
import com.pricemonitor.entity.User;
import com.pricemonitor.service.ProfileService;
import com.pricemonitor.service.UserService;
import com.pricemonitor.tools.JSONConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *  This controller for work with Profile user
 */

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
    public String registerProfile(@RequestBody RegisterProfileDTO dto){
        profileService.createNewProfile(dto.getFullName(), dto.getLogin(), dto.getPassword(), dto.getEmail());
        int tempID = userService.findUserByLogin(dto.getLogin()).getId();
        return Integer.toString(profileService.findProfileByUserId(tempID).getId());
    }

    @PostMapping(path = "/updateProfile")
    public String updateProfile(@RequestBody Profile profile){
        profileService.updateProfile(profile);
        Profile newProfile = profileService.findProfileById(profile.getId());
        JSONConverter converter = new JSONConverter(newProfile);
        return converter.getJSON();
    }

    @PostMapping(path = "/changepassword")
    public String changeUserPassword(@RequestBody UserPasswordDTO dto){
        User currentUser = userService.findUserByLogin(dto.getLogin());
        currentUser.setPassword(dto.getPassword());
        userService.updateUser(currentUser);
        return "success!";
    }

    @PostMapping(path = "/addRole")
    public String addRoleForUser(@RequestBody UserRoleDTO dto){
        userService.addUserRole(dto.getLogin(), dto.getRoleName());
        User user = userService.findUserByLogin(dto.getLogin());
        JSONConverter converter = new JSONConverter(user.getRoles());
        return converter.getJSON();
    }
}
