package com.pricemonitor.service;

import com.pricemonitor.ContextConfigurationTest;
import com.pricemonitor.JPAConfigureTest;
import com.pricemonitor.dto.ProfileDTO;
import com.pricemonitor.entity.Profile;
import com.pricemonitor.entity.Role;
import com.pricemonitor.repository.IProfileRepository;
import com.pricemonitor.repository.IRoleRepository;
import com.pricemonitor.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ContextConfigurationTest.class, JPAConfigureTest.class})
class ProfileServiceTest {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IProfileRepository profileRepository;

    @Autowired
    @Qualifier("roleRepository")
    private IRoleRepository roleRepository;

    @InjectMocks
    private ProfileService profileService;

    @BeforeEach
    void setUp() {
        profileService = mock(ProfileService.class);
    }

    @Test
    void whenCreateNewProfile() {
        String userLogin = "test";
        String userPassw = "test";
        String userEmail = "test";
        String userFullName = "Test test test";

        int countBeforeInsertProfile = profileRepository.getAllProfiles().size();
        int countBeforeInsertUsers = userRepository.getAllUsers().size();

        profileService.createNewProfile(userFullName, userLogin, userPassw, userEmail);

        verify(profileService, times(1)).createNewProfile(userFullName, userLogin, userPassw, userEmail);

        assertEquals(countBeforeInsertProfile, profileRepository.getAllProfiles().size());
        assertEquals(countBeforeInsertUsers, userRepository.getAllUsers().size());

    }

    @Test
    void whenUpdateProfile() {
        ProfileDTO dto = new ProfileDTO();
        dto.setFullName("Abcdef Jhijk Lmnopq");
        dto.setProfileId(1);

        Profile profile = profileRepository.findProfileById(1);
        profile.setFullName(dto.getFullName());


        profileService.updateProfile(dto);

        verify(profileService, times(1)).updateProfile(dto);

        assertEquals("Abcdef Jhijk Lmnopq", profile.getFullName());

    }

    @Test
    void whenAddRoleForUser() {

        Profile profile = profileRepository.findProfileById(1);

        Role role = new Role();
        role.setName("ROLE_TEST");

        profileService.addRoleForUser(profile, role);

        int countRoles = userRepository.findUserById(1).getRoles().size();

        verify(profileService, times(1)).addRoleForUser(profile, role);

        assertEquals(userRepository.findUserById(1).getRoles().size(), countRoles);
    }
}