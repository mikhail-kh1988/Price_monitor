package com.pricemonitor.service;

import com.pricemonitor.dto.ProfileDTO;
import com.pricemonitor.dto.UserPasswordDTO;
import com.pricemonitor.entity.Profile;
import com.pricemonitor.entity.Role;
import com.pricemonitor.entity.User;
import com.pricemonitor.repository.IProfileRepository;
import com.pricemonitor.repository.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод создания профиля по коротким данным. ");
    }

    public void updateProfile(ProfileDTO dto){
        Profile profile = findProfileById(dto.getProfileId());
        profile.setFullName(dto.getFullName());
        profile.setAddress(dto.getAddress());
        profile.setPhone(dto.getPhone());

        profileRepository.updateProfile(profile);
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод обновления профиля. ");
    }

    public void addRoleForUser(Profile profile, Role role){
        Profile findProfile = profileRepository.findProfileById(profile.getId());
        User user = userRepository.findUserById(profile.getUser().getId());

        java.util.List<Role> roles = user.getRoles();
        roles.add(role);
        user.setRoles(roles);

        userRepository.updateUser(user);
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод добавления новой роли "+role.getName()+" пользователю "+profile.getUser().getLogin()+" .");
    }

    public java.util.List<Profile> getAllProfiles(){
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод выведения списка всех профилей.");
        return profileRepository.getAllProfiles();
    }

    public Profile findProfileById(int id){
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод поиска профиля по ID "+id);
        return profileRepository.findProfileById(id);
    }

    public Profile findProfileByUserId(int id){
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод поиска профиля по user_id "+id);
        return profileRepository.findProfileByUserId(id);
    }

    public void changeUserPassword(UserPasswordDTO dto){
        User currentUser =  userService.findUserByLogin(dto.getLogin());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
        String passwd = encoder.encode(dto.getPassword());
        currentUser.setPassword(passwd);
        userService.updateUser(currentUser);
        logger.info("["+this.getClass().getCanonicalName()+"] Вызван метод изменения пароля пользователя.");
    }
}
