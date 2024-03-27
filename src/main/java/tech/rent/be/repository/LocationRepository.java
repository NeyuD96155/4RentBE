package tech.rent.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.rent.be.entity.Category;
import tech.rent.be.entity.Location;

public interface LocationRepository  extends JpaRepository<Location, Long> {
    Location findLocationById(Long id);
}
