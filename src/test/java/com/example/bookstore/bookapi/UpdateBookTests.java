package com.example.bookstore.bookapi;

import com.example.bookstore.base.BaseTestClass;
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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UpdateBookTests extends BaseTestClass {

    @LocalServerPort
    private int port;

    RequestSpecification requestSpec;

    private Book book1;
    private Book book2;

    @Before
    public void setUp() {
        initBooks();
        requestSpec = new RequestSpecBuilder()
                .setBaseUri("http://localhost")
                .setPort(port)
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void updateBook_withValidObject_shouldReturn200() {
        int id = createBookPrecondition(requestSpec, book1);
        book1.setGenres(Arrays.asList(
                Genre.builder().id(2L).build(),
                Genre.builder().id(3L).build()
        ));
        given()
                .spec(requestSpec)
                .body(book1)
                .when()
                .put("/books/" + id)
                .then()
                .assertThat()
                .statusCode(200);

        given()
                .spec(requestSpec)
                .when()
                .get("/books/" + id)
                .then()
                .assertThat()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("genres[0].type", equalTo("Science fiction"))
                .body("genres[1].type", equalTo("Dystopian fiction"));
    }

    @Test
    public void updateBook_withInvalidId_shouldReturn400() {
        int id = createBookPrecondition(requestSpec, book2);
        book2.setId(656L);
        given()
                .spec(requestSpec)
                .body(book2)
                .when()
                .put("/books/" + id)
                .then()
                .assertThat()
                .statusCode(400).body("message", equalTo("ID parameter doesn't match object's ID! Object ID may be omitted."));
    }

    private void initBooks() {
        book1 = Book.builder()
                .isbn("9780451524935")
                .title("1984")
                .author("George Orwell")
                .publicationYear("1961")
                .price("6.82")
                .quantity(2)
                .genres(Arrays.asList(
                        Genre.builder().id(2L).build()
                )).build();

        book2 = Book.builder()
                .isbn("9780060935467")
                .title("To Kill a Mockingbird")
                .author("Harper Lee")
                .publicationYear("2002")
                .price("7.50")
                .quantity(1)
                .genres(Arrays.asList(
                        Genre.builder().id(16L).build(),
                        Genre.builder().id(17L).build()
                )).build();
    }
}
