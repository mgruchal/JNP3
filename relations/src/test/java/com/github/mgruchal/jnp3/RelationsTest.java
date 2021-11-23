package com.github.mgruchal.jnp3;

import com.github.mgruchal.jnp3.relations.users.User;
import com.github.mgruchal.jnp3.relations.users.UsersService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.MediaType;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
public class RelationsTest {

    @InjectMock
    @RestClient
    UsersService usersMock;

    @Test
    public void testHelloEndpoint() {

        var userOne = new User(UUID.randomUUID().toString(), "user-one");
        Mockito.when(usersMock.getOne(userOne.id)).thenReturn(userOne);

        var userTwo = new User(UUID.randomUUID().toString(), "user-two");
        Mockito.when(usersMock.getOne(userTwo.id)).thenReturn(userTwo);

        given()
                .when().get("/users/" + userOne.id + "/followees")
                .then()
                .statusCode(200)
                .body("$.size()", is(0));

        given()
                .body("{\"id\":\"" + userTwo.id + "\"}")
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .when()
                .post("/users/" + userOne.id + "/followees")
                .then()
                .statusCode(200);

        given()
                .when().get("/users/" + userOne.id + "/followees")
                .then()
                .statusCode(200)
                .body("$.size()", is(1));

        given()
                .when().get("/users/id-nonexistent/followees")
                .then()
                .statusCode(404);
    }
}
