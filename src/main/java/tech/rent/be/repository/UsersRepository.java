package tech.rent.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.rent.be.entity.Users;

public interface UsersRepository extends JpaRepository<Users,Long> {

}
