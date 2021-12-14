package org.leafnode.jnp3.service;

import io.quarkus.logging.Log;
import io.quarkus.redis.client.RedisClient;
import io.vertx.core.json.JsonObject;
import io.vertx.redis.client.Response;
import org.leafnode.jnp3.model.User;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.Arrays;

@ApplicationScoped
@Default
public class CachedUserService implements UserService {
    @Inject
    HttpUserService userService;

    @Inject
    RedisClient redisClient;

    @Override
    public User getUser(String username) throws UserNotFound, RuntimeException {
        Log.info("Cached client for username "+username);
        User user;
        Response userResponse = redisClient.get(username);
        if (userResponse != null) {
            String userStr = userResponse.toString();
            if (userStr != null && !userStr.equals("")) {
                Log.info("cache hit");
                JsonObject json = new JsonObject(userStr);
                return json.mapTo(User.class);
            }
        }
        Log.info("cache miss");
        user = userService.getUser(username);
        var userStr = JsonObject.mapFrom(user).toString();
        redisClient.set(Arrays.asList(username, userStr));
        return user;
    }
}
