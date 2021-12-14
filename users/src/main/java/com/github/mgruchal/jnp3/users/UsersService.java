package com.github.mgruchal.jnp3.users;

import com.github.mgruchal.jnp3.users.model.User;

import java.util.Set;
import java.util.UUID;

public interface UsersService {
    User find(UUID id) throws UserNotFound;
    User get(String username) throws UserNotFound;

    void save(User user) throws UserAlreadyExists;

    Set<User> getAll();

    class UserNotFound extends Exception {
    }

    class UserAlreadyExists extends Exception {}
}
