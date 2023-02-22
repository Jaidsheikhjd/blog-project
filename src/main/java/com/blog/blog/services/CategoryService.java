package com.blog.blog.services;

import java.util.List;

import com.blog.blog.payloads.CategoryDto;



public interface CategoryService {
    
    // create
    CategoryDto creatCategory(CategoryDto categoryDto );
   
    // update
    CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);

    // delete
    void deleteCategory(Integer categoryId);

    // get
    CategoryDto getCategory(Integer categoryId);

    // get all
    
    List<CategoryDto> getCategories();
    


}
