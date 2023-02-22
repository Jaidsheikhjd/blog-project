package com.blog.blog.payloads;



import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
//user data transfer object
public class UserDto {
	private int id;
	@NotEmpty
	@Size(min = 2,message = "username must be min of 4 character")
	private String name;
	@Email(message = "email address is not valid !!")
	private String email;
	@NotEmpty
	@Size(min = 3,max = 10,message = "password atleast 4 character")
	private String password;
	@NotEmpty
	private String about;	
	
	// private Set<CommentDto> comments = new HashSet<>();
}
