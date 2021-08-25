package com.pricemonitor.repositories;

import com.pricemonitor.entity.Profile;
import com.pricemonitor.hibernate.CRUDRepository;

public interface IProfileRepository extends CRUDRepository {

    void createProfile(Profile profile);
    void updateProfile(Profile profile);
    Profile findProfileById(int id);
    Profile findProfileByUserId(int uid);
    java.util.List<Profile> getAllProfiles();
}
