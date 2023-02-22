package com.blog.blog.services;

import java.util.List;

import com.blog.blog.entities.Post;
import com.blog.blog.payloads.PostDto;
import com.blog.blog.payloads.PostResponse;

public interface PostService {
        
    // createŌ
      PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
    //   update 
      PostDto updatePost(PostDto postDto,Integer postId);
        //    delete
      void  deletePost(Integer postId);
//       get all post
      PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortdir);
//         get  post by id
      PostDto getPostsById(Integer postId);
//       get all post by category
      List<PostDto> getPostsByCategory(Integer categoryId);
//     get all posts by user
      List<PostDto> getPostsByUser(Integer userId);
    //   search posts
    List<PostDto> searchPosts(String keyword);
         
    List<PostDto> getpost(Integer pageNumber,Integer pageSize,String sortBy,String sortdir);
    }
