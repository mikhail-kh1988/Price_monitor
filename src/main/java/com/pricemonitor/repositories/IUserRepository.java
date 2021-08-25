package com.pricemonitor.repositories;

import com.pricemonitor.entity.User;
import com.pricemonitor.hibernate.CRUDRepository;

public interface IUserRepository extends CRUDRepository {

    void createUser(User user);
    void updateUser(User user);
    User findUserById(int id);
    User findUserByName(String name);
    java.util.List<User> getAllUsers();

}
