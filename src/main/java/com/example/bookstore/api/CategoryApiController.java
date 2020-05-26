package com.example.bookstore.api;

import com.example.bookstore.model.Category;
import com.example.bookstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CategoryApiController implements CategoryApi {

    @Autowired
    private CategoryService categoryService;

    @Override
    public ResponseEntity<List<Category>> getCategories() {
        List<Category> categories = categoryService.getCategories();
        return ResponseEntity.ok(categories);
    }
}
