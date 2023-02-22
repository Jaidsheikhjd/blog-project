package com.blog.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.blog.entities.Category;
import com.blog.blog.exceptions.ResourceNotFoundException;
import com.blog.blog.payloads.CategoryDto;
import com.blog.blog.reositories.CategoryRepo;
import com.blog.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

          @Autowired
          private CategoryRepo categoryRepo;
        
          @Autowired
          private ModelMapper modelMapper;

        //   12:11 video 16


        
    @Override
    public CategoryDto creatCategory(CategoryDto categoryDto) {
     Category cat = this.modelMapper.map(categoryDto, Category.class);
        Category addedCat=this.categoryRepo.save(cat);
        return this.modelMapper.map(addedCat,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
        cat.setCatTitle(categoryDto.getCatTitle());
        cat.setCatDescription(categoryDto.getCatDescription());
        Category updatedcat=this.categoryRepo.save(cat);
      
        return this.modelMapper.map(updatedcat, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
       Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        this.categoryRepo.delete(cat);
    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
         Category cate=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));
        return this.modelMapper.map(cate, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories=this.categoryRepo.findAll();
        List<CategoryDto> catDtos= categories.stream().map((cat)->this.modelMapper.map(cat,CategoryDto.class)).collect(Collectors.toList());
        return catDtos;
    }
    
}
