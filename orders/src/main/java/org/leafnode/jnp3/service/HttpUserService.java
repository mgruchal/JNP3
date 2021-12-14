package org.leafnode.jnp3.service;

import io.vertx.core.json.JsonObject;
import org.leafnode.jnp3.model.User;

import javax.enterprise.context.ApplicationScoped;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import io.quarkus.logging.Log;

@ApplicationScoped
public class HttpUserService implements UserService {
    public User getUser(String username) throws UserNotFound, RuntimeException {
        HttpRequest request;
        URI uri;
        HttpResponse<String> response;

        try {
            uri = new URI("http://users:8080/users/find/"+username);
        } catch (URISyntaxException ignored) {
            throw new RuntimeException();
        }

        request = HttpRequest.newBuilder().uri(uri)
            .GET()
            .build();

        try {
            response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException();
        }

        if (response.statusCode() != 200) {
            throw new UserNotFound();
        }
        String responseAsString = response.body();
        JsonObject json = new JsonObject(responseAsString);
        return json.mapTo(User.class);
    }
}
