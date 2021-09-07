package com.pricemonitor.repository.impl;

import com.pricemonitor.entity.Role;
import com.pricemonitor.hibernate.AbstractRepository;
import com.pricemonitor.repository.IRoleRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;


@Repository
public class RoleRepository extends AbstractRepository implements IRoleRepository {

    public RoleRepository() {
        this.setClazz(Role.class);
    }

    @Transactional
    @Override
    public void createNewRole(Role role) {
        this.create(role);
    }

    @Transactional
    @Override
    public Role findRoleByName(String roleName) {
        CriteriaBuilder criteriaBuilder = this.getEntityManager().getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<Role> query = criteriaBuilder.createQuery(Role.class);
        Root<Role> root = query.from(Role.class);
        query.select(root);
        query.where(criteriaBuilder.equal(root.get("name"), roleName));
        return this.getEntityManager().getEntityManagerFactory().createEntityManager().createQuery(query).getSingleResult();
    }

    @Override
    public java.util.List<Role> getAllRoles() {
        return this.findAll();
    }
}
