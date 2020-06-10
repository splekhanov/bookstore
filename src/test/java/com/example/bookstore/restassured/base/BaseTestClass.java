package com.example.bookstore.restassured.base;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.security.Credentials;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.with;

public class BaseTestClass {

    public int createBookPrecondition(RequestSpecification requestSpec, Book book) {
        String id = given()
                .auth().basic("admin@epam.com", "1234")
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
                .auth().basic("admin@epam.com", "1234")
                .spec(requestSpec)
                .body(book)
                .when()
                .post("/books");
    }
}
