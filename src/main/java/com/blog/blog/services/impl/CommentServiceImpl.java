package com.blog.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blog.entities.Comments;
import com.blog.blog.entities.Post;
import com.blog.blog.exceptions.ResourceNotFoundException;
import com.blog.blog.payloads.CommentDto;
import com.blog.blog.reositories.CommentRepo;
import com.blog.blog.reositories.PostRepo;
import com.blog.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
        @Autowired
        private PostRepo postRepo;
       
        @Autowired
        private CommentRepo commentRepo;
        @Autowired
        private ModelMapper modelMapper;


    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post =this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
        Comments comments= this.modelMapper.map(commentDto, Comments.class);
 
        comments.setPost(post);
       Comments savedComment=this.commentRepo.save(comments);
        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comments com=this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comments", "comment id", commentId));
        this.commentRepo.delete(com);
    }
    
}
