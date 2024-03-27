package tech.rent.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.rent.be.entity.Post;
import tech.rent.be.entity.Resource;

public interface ResourceRepository extends JpaRepository<Resource,Long> {

}