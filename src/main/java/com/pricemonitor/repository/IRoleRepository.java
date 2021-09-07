package com.pricemonitor.repository;

import com.pricemonitor.entity.Role;
import com.pricemonitor.hibernate.CRUDRepository;


public interface IRoleRepository extends CRUDRepository {

    public void createNewRole(Role role);
    public Role findRoleByName(String roleName);
    public java.util.List<Role> getAllRoles();
}
