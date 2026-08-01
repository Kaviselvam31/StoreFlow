package com.storeflow.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.storeflow.entity.Category;
import com.storeflow.repository.CategoryRepository;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // POST API
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    // GET ALL API
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // GET BY ID API
    public Category getCategoryById(int id) {
        return categoryRepository.findById(id).orElse(null);
    }

    // UPDATE API
    public Category updateCategory(int id, Category category) {

        Category existingCategory = categoryRepository.findById(id).orElse(null);

        if (existingCategory != null) {
            existingCategory.setCategoryName(category.getCategoryName());
            existingCategory.setDescription(category.getDescription());

            return categoryRepository.save(existingCategory);
        }

        return null;
    }

    // DELETE API
    public String deleteCategory(int id) {

        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return "Category Deleted Successfully";
        }

        return "Category Not Found";
    }
}