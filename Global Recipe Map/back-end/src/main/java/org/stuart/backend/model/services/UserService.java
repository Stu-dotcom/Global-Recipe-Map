package org.stuart.backend.model.services;

import org.stuart.backend.model.dto.UserDTO;
import org.stuart.backend.model.entities.User;

import java.util.Optional;

public interface UserService {
    Boolean registerUser(UserDTO user);
    Optional<String> authenticateUser(UserDTO user);
    Iterable<User> getAllUsers();
    User updateUser(Long id, User userDetails);
    boolean deleteUser(Long id);
    User findByUsername(String username);
}

