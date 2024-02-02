package tech.rent.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.rent.be.dto.PostRequestDTO;
import tech.rent.be.entity.Post;
import tech.rent.be.services.PostService;

@RestController
@CrossOrigin
public class PostController {
    @Autowired
    PostService postService;
    @PostMapping("/post")
    public ResponseEntity post(@RequestBody PostRequestDTO postRequestDTO){
        Post post = postService.createPost(postRequestDTO);
        return  ResponseEntity.ok(post);
    }
}
