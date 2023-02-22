package com.blog.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.blog.entities.Category;
import com.blog.blog.entities.Post;
import com.blog.blog.entities.User;
import com.blog.blog.exceptions.ResourceNotFoundException;
import com.blog.blog.payloads.PostDto;
import com.blog.blog.payloads.PostResponse;
import com.blog.blog.reositories.CategoryRepo;
import com.blog.blog.reositories.PostRepo;
import com.blog.blog.reositories.UserRepo;
import com.blog.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {
      @Autowired 
    private PostRepo postRepo;
       @Autowired
       private UserRepo userRepo;
     @Autowired
     private ModelMapper modelMapper;
     @Autowired
     private CategoryRepo categoryRepo;
//  video 19 17:41
    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
     User user =this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "User Id", userId));
       Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","category Id",categoryId));
     Post post= this.modelMapper.map(postDto, Post.class);
          post.setImageName("default.png");
          post.setAddedDate(new Date());
          post.setUser(user);
          post.setUser(user);
          post.setCategory(category);
      Post newPost=this.postRepo.save(post);
           return this.modelMapper.map(newPost, PostDto.class) ;
    }

    @Override
    public void deletePost(Integer postId) {
      Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post","post Id",postId));
         this.postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortdir) {
       Sort sort =(sortdir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
       
      
      PageRequest p = PageRequest.of(pageNumber,pageSize,sort);
          Page<Post> pagePost=this.postRepo.findAll(p);
          List<Post> allpost=pagePost.getContent();
    //     List<Post> allpost= this.postRepo.findAll();
       List<PostDto> posts=allpost.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
    
       PostResponse postResponse = new PostResponse();
       postResponse.setContent(posts);
       postResponse.setPageNumber(pagePost.getNumber());
       postResponse.setPageSize(pagePost.getSize());
       postResponse.setTotalElement(pagePost.getTotalElements());
       postResponse.setTotalPages(pagePost.getTotalPages());
       postResponse.setLastPage(pagePost.isLast());
       return postResponse;
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
      Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","category id",categoryId));
       List<Post> posts=this.postRepo.findByCategory(cat);
    List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
       return postDtos;
    }

    @Override
    public PostDto getPostsById(Integer postId) {
       Post post= this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","post id",postId));
       return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
           User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "user Id", userId));
       List<Post> posts=this.postRepo.findByUser(user);
       List<PostDto> postDtos=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
           return postDtos;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
          List<Post> posts=this.postRepo.findByTitleContaining(keyword);
          List<PostDto> postDtos =posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
      return postDtos;
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post= this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","post id",postId));
       post.setTitle(postDto.getTitle());
       post.setContent(postDto.getContent());
       post.setImageName(postDto.getImageName());
         
       Post updatePost=this.postRepo.save(post);   
       return this.modelMapper.map(updatePost, PostDto.class);
    }
    @Override
    public List<PostDto> getpost(Integer pageNumber,Integer pageSize,String sortBy,String sortdir) {
       Sort sort =(sortdir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        
       PageRequest p = PageRequest.of(pageNumber,pageSize,sort);
             
          Page<Post> pagePost=this.postRepo.findAll(p);
          List<Post> allpost=pagePost.getContent();
    //     List<Post> allpost= this.postRepo.findAll();
       List<PostDto> posts=allpost.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
    
       PostResponse postResponse = new PostResponse();
       postResponse.setContent(posts);
       postResponse.setPageNumber(pagePost.getNumber());
       postResponse.setPageSize(pagePost.getSize());
       postResponse.setTotalElement(pagePost.getTotalElements());
       postResponse.setTotalPages(pagePost.getTotalPages());
       postResponse.setLastPage(pagePost.isLast());
       return posts;
    }
    
}
