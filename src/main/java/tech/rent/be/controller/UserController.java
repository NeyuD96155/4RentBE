package tech.rent.be.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.rent.be.dto.UserDTO;
import tech.rent.be.services.UserService;
import tech.rent.be.utils.AccountUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "api")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    // Other autowiring and methods

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        if (!users.isEmpty()) {
            return ResponseEntity.ok(users);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("profile")
    public ResponseEntity<UserDTO> getUserById() {
        try {
            UserDTO userDTO = userService.getUserData();
            return ResponseEntity.ok(userDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
        // Or you could use @ExceptionHandler to handle exceptions globally
    }

    @PutMapping("/update") // We specify the path to update the user details
    public ResponseEntity<UserDTO> updateProfile(@RequestBody UserDTO userDTO) {
        try {
            UserDTO updatedUserDTO = userService.updateUserData(userDTO);
            return ResponseEntity.ok(updatedUserDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
