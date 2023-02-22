package com.blog.blog.controllers;

import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.payloads.Apiresponse;
import com.blog.blog.payloads.UserDto;
import com.blog.blog.services.UserService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
// @RequestMapping("/api")
public class UserControlllers {
       
    @Autowired
    private UserService userService;
    
//   get 
  @GetMapping("/user")
  public ResponseEntity<List<UserDto>> getAllUsers() {
      return ResponseEntity.ok(this.userService.getAllUsers());
  }

    //  GETBYID-USER BY ID GET
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer uid) {
        return ResponseEntity.ok(this.userService.getUserById(uid));
    }


    // post-create user
    @PostMapping("/user") 
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto  ){
        UserDto creatUserDto = this.userService.createUser(userDto);
        return new ResponseEntity<>(creatUserDto,HttpStatus.CREATED);
    }
 
    //   put- UPDATE user
   @PutMapping("/user/{userId}")
   public ResponseEntity<UserDto> updateUser(@Valid @PathVariable("userId") Integer uid, @RequestBody UserDto userDto) {
         UserDto updatedUser=this.userService.updateUser(userDto, uid);
       return ResponseEntity.ok(updatedUser);
   }

    //  ADMIN ACCES ONLY
//     DELETE -DELETE USER
@PreAuthorize("hasRole('ADMIN')")
@DeleteMapping("/user/{userId}")
public ResponseEntity<Apiresponse> deleteUser(@PathVariable("userId")Integer uid ) {
      this.userService.deleteUser(uid);
     return new ResponseEntity<Apiresponse>(new Apiresponse("User deleted",true),HttpStatus.OK);
}

}
