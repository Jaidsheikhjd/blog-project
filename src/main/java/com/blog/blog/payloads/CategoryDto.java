package com.blog.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    
  private int categoryId;
  @NotBlank
  @Size(min = 2,message = "min title of 1 charactor")
  private String catTitle;
  @NotBlank
  @Size(min = 10,message = "min size of dicription is 10")
  private String catDescription;
}
