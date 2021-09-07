package com.pricemonitor.repository.impl;

import com.pricemonitor.entity.User;
import com.pricemonitor.hibernate.AbstractRepository;
import com.pricemonitor.repository.IUserRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserRepository extends AbstractRepository implements IUserRepository {

    public UserRepository() {
        this.setClazz(User.class);
    }

    @Transactional
    @Override
    public void createUser(User user) {
        this.create(user);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        this.update(user);
    }

    @Override
    public User findUserById(int id) {
        return (User) this.findOneById(id);
    }

    @Override
    public User findUserByName(String name) {
        CriteriaBuilder criteriaBuilder = this.getEntityManager().getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        query.select(root);
        query.where(criteriaBuilder.equal(root.get("login"), name));
        return this.getEntityManager().getEntityManagerFactory().createEntityManager().createQuery(query).getSingleResult();
    }

    @Override
    public List<User> getAllUsers() {
        return this.findAll();
    }
}
