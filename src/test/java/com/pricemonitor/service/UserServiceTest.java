package com.pricemonitor.service;

import com.pricemonitor.ContextConfigurationTest;
import com.pricemonitor.JPAConfigureTest;
import com.pricemonitor.entity.User;
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
class UserServiceTest {

    @Autowired
    @Qualifier("userRepository")
    private IUserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = mock(UserService.class);
    }

    @Test
    void whenCreateNewUser() {
        User user = new User();
        user.setLogin("test");
        user.setPassword("test");

        int countUserBeforeInsert = userRepository.getAllUsers().size();

        userService.createNewUser(user);

        verify(userService, times(1)).updateUser(user);

        assertEquals(countUserBeforeInsert, userRepository.getAllUsers().size());
    }

    @Test
    void whenUpdateUser() {

        User user = userRepository.findUserByName("admin");
        user.setLogin("test");

        userService.updateUser(user);

        verify(userService, times(1)).updateUser(user);

        assertEquals("test", user.getLogin());
    }
}