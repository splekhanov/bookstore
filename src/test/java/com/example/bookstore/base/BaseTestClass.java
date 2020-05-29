package com.example.bookstore.base;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.Genre;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class BaseTestClass {

    public int createBookPrecondition(RequestSpecification requestSpec, Book book) {
        String id = given()
                .spec(requestSpec)
                .body(book)
                .when()
                .post("/books")
                .then()
                .assertThat()
                .statusCode(201)
                .extract()
                .body().jsonPath().getString("id");
        return Integer.parseInt(id);
    }

    public void createBookPreconditionNoValidation(RequestSpecification requestSpec, Book book) {
        given()
                .spec(requestSpec)
                .body(book)
                .when()
                .post("/books");
    }
}
