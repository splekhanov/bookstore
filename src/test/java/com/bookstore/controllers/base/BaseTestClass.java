package com.bookstore.controllers.base;

import com.bookstore.model.Book;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

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
