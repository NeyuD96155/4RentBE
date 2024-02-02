package tech.rent.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.rent.be.entity.RealEstate;

public interface RealEstateRepository  extends JpaRepository<RealEstate,Long> {

}
