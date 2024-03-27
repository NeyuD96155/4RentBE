package tech.rent.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.rent.be.entity.Category;

public interface CategoryRepository  extends JpaRepository<Category, Long> {
    Category findCategoryById(Long id);
}
