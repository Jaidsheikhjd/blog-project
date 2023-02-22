package com.blog.blog.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blog.blog.config.appConstants;
import com.blog.blog.payloads.Apiresponse;
import com.blog.blog.payloads.PostDto;
import com.blog.blog.payloads.PostResponse;
import com.blog.blog.services.FileService;
import com.blog.blog.services.PostService;




@RestController
@RequestMapping("/Post")
public class PostController {
     
       @Autowired
        private PostService postService;
       @Autowired
        private FileService fileService;
       @Value("${project.image}")
       private String path;

    @PostMapping(value="/user/{userId}/category/{categoryId}/post")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId)
   {
       PostDto creatPost=this.postService.createPost(postDto, userId, categoryId) ;
        return new ResponseEntity<PostDto>(creatPost,HttpStatus.CREATED);
    }

    // get by user
    @GetMapping(value="/user/{userId}/post")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
       List<PostDto> posts=this.postService.getPostsByUser(userId);
        
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    // get by category
    @GetMapping(value="/category/{categoryId}/post")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId) {
       List<PostDto> posts=this.postService.getPostsByCategory(categoryId);
        
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    // get all post
     @GetMapping(value="/posts")
     public ResponseEntity<PostResponse>  getAllPost(
        @RequestParam(value = "pageNumber",defaultValue =appConstants.PAGE_NUMBER,required = false)Integer pageNumber,
        @RequestParam(value = "pageSize",defaultValue = appConstants.PAGE_SIZE,required = false)Integer pageSize ,
        @RequestParam(value = "sortBy",defaultValue = appConstants.SORT_BY,required = false)String sortBy,
        @RequestParam(value = "sortdir",defaultValue = appConstants.SORT_DIR,required = false)String sortdir
        ) {
       PostResponse postResponse =this.postService.getAllPost(pageNumber,pageSize,sortBy,sortdir);
        return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
     }
    // get post by id
    @GetMapping(value="/post/{postId}")
     public ResponseEntity<PostDto>  getPostById(@PathVariable Integer postId) {
    PostDto postDto=this.postService.getPostsById(postId);
        return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
     }

     // delete post
     @DeleteMapping("/post/{postId}")
      public Apiresponse deletepost(@PathVariable Integer postId) {
    this.postService.deletePost(postId);
      return new Apiresponse("post is successfully deleted",true);
         }

         @PutMapping(value="/post/{postId}")
         public ResponseEntity<PostDto> updatePost(@PathVariable Integer postId, @RequestBody PostDto postDto) {
           PostDto updatePost = this.postService.updatePost(postDto, postId);
             
             return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK) ;
         }

      // search post
      @GetMapping(value="/post/search/{keywords}")
      public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords) {
         List<PostDto> result = this.postService.searchPosts(keywords);
        return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK) ;
      }
      
   //   post image upload  
     @PostMapping(value="/post/image/upload/{postId}")
     public ResponseEntity<PostDto> uploadIPostImage(@RequestParam("image") MultipartFile image,@PathVariable Integer postId)throws IOException {
        
      PostDto postDto =this.postService.getPostsById(postId);
         
        String fileName =this.fileService.uploadImage(path, image);        
        postDto.setImageName(fileName);
       PostDto updatedPost = this.postService.updatePost(postDto, postId);
       return new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
     }

   //   method to serve files
   @GetMapping(value="/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
   public void downloadeImage(
      @PathVariable("imageName")String imageName,HttpServletResponse response
   )throws IOException {
      InputStream resource = this.fileService.getResource(path, imageName);
       response.setContentType(MediaType.IMAGE_JPEG_VALUE);
      StreamUtils.copy(resource, response.getOutputStream());
   }
   
     

}
