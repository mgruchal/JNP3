package com.github.mgruchal.jnp3;

import com.github.mgruchal.jnp3.model.User;

import java.util.Set;
import java.util.UUID;

public interface UsersService {
    User find(UUID id) throws UserNotFound;

    void save(User user) throws UserAlreadyExists;

    Set<User> getAll();

    class UserNotFound extends Exception {
    }

    class UserAlreadyExists extends Exception {}
}
