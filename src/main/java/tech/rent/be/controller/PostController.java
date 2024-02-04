package tech.rent.be.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.rent.be.dto.PostRequestDTO;

@RestController
@CrossOrigin
public class PostController {
    @PostMapping("/post")
    public ResponseEntity post(@RequestBody PostRequestDTO postRequestDTO){

        return  ResponseEntity.ok(postRequestDTO);
    }
}
