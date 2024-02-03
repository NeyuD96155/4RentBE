package tech.rent.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.rent.be.dto.PostRequestDTO;
import tech.rent.be.entity.Post;
import tech.rent.be.entity.Users;
import tech.rent.be.repository.PostRepository;
import tech.rent.be.repository.UsersRepository;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;
    @Autowired
    UsersRepository usersRepository;

    public Post createPost(PostRequestDTO postRequestDTO){
        Users users = usersRepository.findUsersById(postRequestDTO.getUserId());
        Post post = new Post();
        post.setContent(postRequestDTO.getContent());
        post.setTitle(postRequestDTO.getTitle());
        post.setPrice(postRequestDTO.getPrice());
        post.setPostDate(postRequestDTO.getPostDate());
        post.setUser(users);
        return postRepository.save(post);

    }


}