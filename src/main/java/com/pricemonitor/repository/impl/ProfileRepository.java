package com.pricemonitor.repository.impl;

import com.pricemonitor.entity.Profile;
import com.pricemonitor.hibernate.AbstractRepository;
import com.pricemonitor.repository.IProfileRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ProfileRepository extends AbstractRepository implements IProfileRepository {

    public ProfileRepository() {
        this.setClazz(Profile.class);
    }

    @Override
    @Transactional
    public void createProfile(Profile profile) {
        this.create(profile);
    }

    @Override
    @Transactional
    public void updateProfile(Profile profile) {
        this.update(profile);
    }

    @Override
    public Profile findProfileById(int id) {
        return (Profile) this.findOneById(id);
    }

    @Override
    public Profile findProfileByUserId(int uid) {
        CriteriaBuilder criteriaBuilder = this.getEntityManager().getEntityManagerFactory().getCriteriaBuilder();
        CriteriaQuery<Profile> query = criteriaBuilder.createQuery(Profile.class);
        Root<Profile> root = query.from(Profile.class);
        query.select(root);
        query.where(criteriaBuilder.equal(root.get("user"), uid));
        return this.getEntityManager().getEntityManagerFactory().createEntityManager().createQuery(query).getSingleResult();
    }

    @Override
    public List<Profile> getAllProfiles() {
        return this.findAll();
    }
}
