package com.blog.blog.reositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.blog.blog.entities.User;

@EnableJpaRepositories
@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
       
    Optional<User> findByEmail(String email);


}
