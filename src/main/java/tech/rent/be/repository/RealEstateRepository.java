package tech.rent.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tech.rent.be.entity.RealEstate;
import tech.rent.be.entity.Users;
import tech.rent.be.enums.EstateStatus;

import java.util.List;

public interface RealEstateRepository  extends JpaRepository<RealEstate,Long> {
    RealEstate findRealEstateById(long id);
    List<RealEstate> findRealEstatesByUsers(Users user);
    List<RealEstate> findRealEstatesByEstateStatus(EstateStatus status);
}
