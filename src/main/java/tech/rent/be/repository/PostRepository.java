package tech.rent.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.rent.be.entity.Post;

public interface PostRepository extends JpaRepository<Post,Long> {

}
