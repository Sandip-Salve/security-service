package com.app.securityservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.securityservice.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

}
