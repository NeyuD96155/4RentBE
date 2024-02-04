package tech.rent.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import tech.rent.be.repository.PostRepository;

public class PostService {


    @Autowired
    PostRepository postRepository;


}