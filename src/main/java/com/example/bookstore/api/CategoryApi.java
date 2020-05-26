package com.example.bookstore.api;

import com.example.bookstore.model.Category;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Api(value = "categories", tags = "Categories", description = "Categories API")
public interface CategoryApi {

    @ApiOperation(value = "Get all existing categories", nickname = "getCategories", response = Category.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category list", response = Category.class)})
    @RequestMapping(value = "/categories",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<Category>> getCategories();

}
