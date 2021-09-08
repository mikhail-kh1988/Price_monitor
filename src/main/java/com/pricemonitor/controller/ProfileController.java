package com.pricemonitor.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pricemonitor.dto.ProfileDTO;
import com.pricemonitor.dto.RegisterProfileDTO;
import com.pricemonitor.dto.UserPasswordDTO;
import com.pricemonitor.dto.UserRoleDTO;
import com.pricemonitor.entity.User;
import com.pricemonitor.service.ProfileService;
import com.pricemonitor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> getAllProfiles() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return new ResponseEntity<>(mapper.writeValueAsString(profileService.getAllProfiles()), HttpStatus.OK);
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
    public ResponseEntity<String> addRoleForUser(@RequestBody UserRoleDTO dto) throws JsonProcessingException{
        userService.addUserRole(dto.getLogin(), dto.getRoleName());
        User user = userService.findUserByLogin(dto.getLogin());
        ObjectMapper mapper = new ObjectMapper();
        return new ResponseEntity<>(mapper.writeValueAsString(user.getRoles()), HttpStatus.OK);
    }
}
