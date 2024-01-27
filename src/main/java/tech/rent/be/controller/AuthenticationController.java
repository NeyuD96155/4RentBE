package tech.rent.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.rent.be.dto.RegisterRequestDTO;
import tech.rent.be.entity.Users;
import tech.rent.be.services.AuthenticationService;

@RestController

public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("register")

    public ResponseEntity register(@RequestBody Users dto){
        Users users = authenticationService.register(dto);
            return ResponseEntity.ok(users);

    }
}
