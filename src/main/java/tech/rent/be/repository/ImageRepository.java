package tech.rent.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.rent.be.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
