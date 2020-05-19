package com.example.bookstore;

import com.example.bookstore.model.Book;
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

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookApiIntegrationTests {

    @LocalServerPort
    private int port;

    RequestSpecification requestSpec;

    private Book book1;
    private Book book2;
    private Book book3;

    @Before
    public void setUp() {
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(port)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        book1 = new Book("9781400164240", "The Great Gatsby", "F. Scott Fitzgerald", "2009", "12.34");
        book2 = new Book("9780140864168", "Lord of the Flies", "William Golding", "1997", "12.34");
        book3 = new Book("9780898459159", "The Grapes of Wrath", "John Steinbeck", "1989", "15.97");
    }

    @Test
    public void createBookTest() {
        given()
                .spec(requestSpec)
                .body(book1)
				.when()
                .post("/books")
                .then()
				.assertThat()
                .statusCode(200).log().all();
    }

    @Test
    public void createExistingBookTest() {
        createBookPrecondition(book2);
        given()
                .spec(requestSpec)
                .body(book2)
				.when()
                .post("/books")
                .then()
				.assertThat()
                .statusCode(409);
    }

    @Test
    public void getBookTest() {
        int id = createBookPrecondition(book3);
        given()
                .spec(requestSpec)
				.when()
                .get("/books/" + id)
                .then()
				.assertThat()
                .statusCode(200)
                .body("id", equalTo(id));
    }

    @Test
    public void getNonExistingBookTest() {
        int id = 999;
        given()
                .spec(requestSpec)
                .when()
                .get("/books/" + id)
                .then()
                .assertThat()
                .statusCode(404);
    }

    public int createBookPrecondition(Book book) {
        String id = given()
                        .spec(requestSpec)
                        .body(book)
                        .when()
                        .post("/books")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract()
                        .body().jsonPath().getString("id");
        return Integer.parseInt(id);
    }
}
