package com.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.project.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	Role findByRole(String role);
}