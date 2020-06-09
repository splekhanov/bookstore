package com.example.bookstore.base;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.security.Credentials;
import com.example.bookstore.model.security.Token;
import com.example.bookstore.model.security.User;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.with;
import static org.hamcrest.CoreMatchers.equalTo;

public class BaseTestClass {

    public String generateToken(int port) {
        String url = "http://localhost:" + port + "/users/auth";
        Credentials creds = Credentials.builder().username("admin@epam.com").password("1234").build();
        return given().accept(ContentType.JSON).contentType(ContentType.JSON).log().all().body(creds).when().post(url).then().assertThat()
                .statusCode(200).extract().body().jsonPath().getString("access_token");
    }

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
