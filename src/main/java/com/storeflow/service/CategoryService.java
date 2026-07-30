package com.storeflow.service;

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
}