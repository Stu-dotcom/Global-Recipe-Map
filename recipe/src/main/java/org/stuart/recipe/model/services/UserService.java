package org.stuart.recipe.model.services;

import org.stuart.recipe.model.entities.User;
import java.util.List;

public interface UserService {
    User saveUser(User user);
    User getUserById(Long id);
    Iterable<User> getAllUsers();
    User updateUser(Long id, User userDetails);
    boolean deleteUser(Long id);
    User findByUsername(String username);
}

