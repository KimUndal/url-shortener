package com.undal.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UrlShortenerResourceITTest {



    @Test
    @Order(1)
    void getUrlShortenerNotNull() {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonItem = objectMapper.createObjectNode();
        jsonItem.put("alias", "myAliasIT");
        jsonItem.put("url", "http://www.google.com");

        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonItem.toString())
                .post("api/v1")
                .then()
                .assertThat()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("size()", is(1));
    }

    @Test
    @Order(2)
    void getUrlShortenerNull() {
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .post("api/v1")
                .then()
                .assertThat()
                .statusCode(Response.Status.BAD_REQUEST.getStatusCode());

    }


    @Test
    @Order(2)
    void getUrlShortener() {
        given()
                .get("/api/v1/myAliasIT")
                .then()
                .assertThat()
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    @Order(2)
    void getUrlShortenerNotFound() {
        given()
                .get("/api/v1/aliasNotFound")
                .then()
                .assertThat()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
    }
}