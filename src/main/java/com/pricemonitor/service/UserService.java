package com.pricemonitor.service;

import com.pricemonitor.entity.Role;
import com.pricemonitor.entity.User;
import com.pricemonitor.repository.IRoleRepository;
import com.pricemonitor.repository.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    public void createNewUser(User user){
        userRepository.createUser(user);
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод созадния пользователя. ");
    }

    public void updateUser(User user){
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод обновления пользователя. ");
        userRepository.updateUser(user);
    }

    public User findUserByLogin(String login){
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод поиска пользователя по login-у: "+login );
        return userRepository.findUserByName(login);
    }

    public void addUserRole(String login, String role){
        User tempUser = userRepository.findUserByName(login);
        Role role1 = roleRepository.findRoleByName(role);

        java.util.List<Role> roleList = new ArrayList<>();
        roleList.add(role1);

        tempUser.setRoles(roleList);
        userRepository.updateUser(tempUser);
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод добавления пользователю роли "+role1.getName());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByName(username);

        if (user == null){
            throw new UsernameNotFoundException("User not found!");
        }
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод поиска пользоватея по username: "+username);
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
