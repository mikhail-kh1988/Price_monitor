package com.pricemonitor.controller;

import com.pricemonitor.dto.ProfileDTO;
import com.pricemonitor.dto.RegisterProfileDTO;
import com.pricemonitor.dto.UserPasswordDTO;
import com.pricemonitor.dto.UserRoleDTO;
import com.pricemonitor.entity.Profile;
import com.pricemonitor.entity.User;
import com.pricemonitor.service.ProfileService;
import com.pricemonitor.service.UserService;
import com.pricemonitor.tools.JSONConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public ResponseEntity<String> getAllProfiles(){
        JSONConverter converter = new JSONConverter(profileService.getAllProfiles());
        return new ResponseEntity<>(converter.getJSON(), HttpStatus.OK);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<String> registerProfile(@RequestBody RegisterProfileDTO dto){
        profileService.createNewProfile(dto.getFullName(), dto.getLogin(), dto.getPassword(), dto.getEmail());
        int tempID = userService.findUserByLogin(dto.getLogin()).getId();
        return new ResponseEntity<>(Integer.toString(profileService.findProfileByUserId(tempID).getId()), HttpStatus.OK);
    }

    @PostMapping(path = "/updateProfile")
    public ResponseEntity<String> updateProfile(@RequestBody ProfileDTO dto){
        profileService.updateProfile(dto);
        return new ResponseEntity<>("success!", HttpStatus.OK);
    }

    @PostMapping(path = "/changepassword")
    public ResponseEntity<String> changeUserPassword(@RequestBody UserPasswordDTO dto){
        profileService.changeUserPassword(dto);
        return new ResponseEntity<>("success!", HttpStatus.OK);
    }

    @PostMapping(path = "/addRole")
    public ResponseEntity<String> addRoleForUser(@RequestBody UserRoleDTO dto){
        userService.addUserRole(dto.getLogin(), dto.getRoleName());
        User user = userService.findUserByLogin(dto.getLogin());
        JSONConverter converter = new JSONConverter(user.getRoles());
        return new ResponseEntity<>(converter.getJSON(), HttpStatus.OK);
    }
}
