package com.blog.blog.reositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.blog.entities.Comments;

public interface CommentRepo extends JpaRepository<Comments,Integer> {
    
}
