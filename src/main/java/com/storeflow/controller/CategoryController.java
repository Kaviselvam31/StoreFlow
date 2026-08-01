package com.storeflow.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.storeflow.entity.Category;
import com.storeflow.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // POST API
    @PostMapping
    public Category saveCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    // GET ALL API
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // GET BY ID API
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable int id) {
        return categoryService.getCategoryById(id);
    }

    // UPDATE API
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable int id,
                                   @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }

    // DELETE API
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable int id) {
        return categoryService.deleteCategory(id);
    }
}