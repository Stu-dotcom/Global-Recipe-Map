package org.stuart.backend.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stuart.backend.model.entities.User;
import org.stuart.backend.model.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    // Save a new user
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Retrieve a user by their ID
    @Override
    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    // Get a list of all users
    @Override
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Update an existing user by ID
    @Override
    public User updateUser(Long id, User userDetails) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            user.setPassword(userDetails.getPassword());
            return userRepository.save(user);
        } else {
            //TODO: throw exception for better error handling
            return null;
        }
    }

    // Delete a user by ID
    @Override
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Find a user by username
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

