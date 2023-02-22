package com.blog.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.entities.Comments;
import com.blog.blog.payloads.Apiresponse;
import com.blog.blog.payloads.CommentDto;
import com.blog.blog.services.CommentService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/Comments")
public class CommentController {
       
    @Autowired 
    private CommentService commentService;

    @PostMapping(value="/post/{postId}")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comments, @PathVariable Integer postId) {
       CommentDto createComment=this.commentService.createComment(comments, postId);
        
        return new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);
    }
    
    @DeleteMapping(value = "/comments/{commentId}")
    public ResponseEntity<Apiresponse> deleteComment(@PathVariable Integer commentId){
    
         this.commentService.deleteComment(commentId);
        return new ResponseEntity<Apiresponse>(new Apiresponse("comment deleted !!",true),HttpStatus.OK);
        }
    




}
