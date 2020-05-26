package com.example.bookstore.service;

import com.example.bookstore.db.category.CategoryRepository;
import com.example.bookstore.exceptions.AlreadyExistException;
import com.example.bookstore.exceptions.NotFoundException;
import com.example.bookstore.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category category) {
        categoryRepository.findCategoryByCategory(category.getCategory()).ifPresent(e -> {
            throw new AlreadyExistException("Category '" + e.getCategory() + "' is already exists");
        });
        return categoryRepository.save(category);
    }

    public List<Category> getCategories() throws NotFoundException {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }
}
