package org.stuart.backend.model.services;

import org.stuart.backend.model.entities.User;

public interface UserService {
    User saveUser(User user);
    User getUserById(Long id);
    Iterable<User> getAllUsers();
    User updateUser(Long id, User userDetails);
    boolean deleteUser(Long id);
    User findByUsername(String username);
}

