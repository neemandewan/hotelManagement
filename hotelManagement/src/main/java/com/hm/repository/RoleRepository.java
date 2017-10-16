package com.hm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hm.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	Role findByRole(String role);

}
