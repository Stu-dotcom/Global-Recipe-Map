package org.stuart.backend.controller;

import org.stuart.backend.model.dto.UserDTO;
import org.stuart.backend.model.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint for user sign-up
    @CrossOrigin
    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> registerUser(@RequestBody UserDTO user) {
        System.out.println("Signing up new user" + user.getUsername());
        boolean isUserCreated = userService.registerUser(user);
        Map<String, String> response = new HashMap<>();

        if (isUserCreated) {
            response.put("message", "User created successfully");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            response.put("message", "Username already taken");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    // Endpoint for user login
    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody UserDTO user) {
        Optional<String> token = userService.authenticateUser(user);
        Map<String, String> response = new HashMap<>();

        if (token.isPresent()) {
            response.put("message", "Login successful");
            response.put("token", token.get()); // Include the token in the JSON response
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
