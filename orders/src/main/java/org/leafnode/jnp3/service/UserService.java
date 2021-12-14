package org.leafnode.jnp3.service;

import org.leafnode.jnp3.model.User;

public interface UserService {
    User getUser(String username) throws UserNotFound, RuntimeException;
}
