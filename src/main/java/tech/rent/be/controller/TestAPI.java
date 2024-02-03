package tech.rent.be.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.rent.be.dto.Test;

@RestController
public class TestAPI {

    @GetMapping("test")
    public ResponseEntity test(){
        Test test = new Test();
        test.name = "aaa";
        return ResponseEntity.ok(test);
    }

}
