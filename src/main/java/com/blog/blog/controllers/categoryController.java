package com.blog.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.payloads.Apiresponse;
import com.blog.blog.payloads.CategoryDto;
import com.blog.blog.services.CategoryService;


@RestController
@RequestMapping("/categories")
public class categoryController {

    @Autowired
    private CategoryService categoryService;

                 // video 16
        // create caete
  @PostMapping(value="/category")
  public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto catogDto) {
    CategoryDto createCat=this.categoryService.creatCategory(catogDto);  
      return new ResponseEntity<CategoryDto>(createCat,HttpStatus.CREATED);
  }
        // Update 
        @PutMapping(value="/category/{catId}")
        public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer catId) {
          CategoryDto updatedCat=this.categoryService.updateCategory(categoryDto,catId);  
            return new ResponseEntity<CategoryDto>(updatedCat,HttpStatus.OK);
        }

        // delete
        @DeleteMapping(value="/category/{catId}")
        public ResponseEntity<Apiresponse> deleteCategory(@PathVariable Integer catId) {
          this.categoryService.deleteCategory(catId);  
            return new ResponseEntity<Apiresponse>(new Apiresponse("category is delete successfully",true),HttpStatus.OK);
        }

        // get single
        @GetMapping(value="/category/{catId}")
        public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId) {
          
            CategoryDto categorydto = this.categoryService.getCategory(catId);
            return new ResponseEntity<CategoryDto>(categorydto,HttpStatus.OK);
        }

            // get all
            @GetMapping(value="/category")
            public ResponseEntity<List<CategoryDto>> getCategories() {
              
              List<CategoryDto> categories = this.categoryService.getCategories();
            return ResponseEntity.ok(categories);
            }


}
