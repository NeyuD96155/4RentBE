package tech.rent.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.rent.be.dto.LoginRequestDTO;
import tech.rent.be.dto.LoginResponseDTO;
import tech.rent.be.dto.RegisterRequestDTO;
import tech.rent.be.entity.Users;
import tech.rent.be.services.AuthenticationService;

@RestController
@CrossOrigin
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("register")
    public ResponseEntity register(@RequestBody Users dto) {
        Users users = authenticationService.register(dto);
        return ResponseEntity.ok(users);

    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO users = authenticationService.login(loginRequestDTO);
        return ResponseEntity.ok(users);
    }

    @GetMapping("active")
    public ResponseEntity activeAccount(@RequestParam String token) {
        authenticationService.activeAccount(token);
        return ResponseEntity.ok("Successfully active account!");
    }

    // ORM: Object Relationship mappimg
}
