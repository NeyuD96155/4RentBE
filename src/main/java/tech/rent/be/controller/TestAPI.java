package tech.rent.be.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.rent.be.dto.Test;
import tech.rent.be.utils.AccountUtils;

@RestController
@SecurityRequirement(name = "api")
public class TestAPI {

    @Autowired
    AccountUtils accountUtils;

    @GetMapping("test")
    public ResponseEntity test(){
        return ResponseEntity.ok(accountUtils.getCurrentUser());
    }

}
