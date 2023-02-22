package com.blog.blog.reositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blog.entities.Role;

public interface RoleRepo extends JpaRepository<Role,Integer> {
    
}
