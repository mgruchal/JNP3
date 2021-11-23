package com.github.mgruchal.jnp3;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.path.json.config.JsonPathConfig;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;

@QuarkusTest
public class UserControllerTest {

    @Test
    public void testEmptyService() {
        given()
                .when().get("/users")
                .then()
                .statusCode(200)
                .body("$.size()", is(0));
    }

    @Test
    public void testRegistration() {
        String registerAdamBody = "{\"username\": \"adam\", \"email\": \"adam@example.com\"}";
        var registration = given()
                .body(registerAdamBody)
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .when()
                .post("/users")
                .then()
                .statusCode(200);

        var userId = registration.extract().body().jsonPath(JsonPathConfig.jsonPathConfig()).getString("id");

        given().body(registerAdamBody)
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .when()
                .post("/users")
                .then()
                .statusCode(400);

        given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)
                .body("username", containsInAnyOrder("adam"),
                        "email", containsInAnyOrder("adam@example.com"),
                        "id", containsInAnyOrder(userId));

        given()
                .when()
                .get("/users/" + userId)
                .then()
                .statusCode(200);
    }
}
