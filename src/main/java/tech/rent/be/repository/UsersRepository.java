package tech.rent.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.rent.be.entity.Users;
import tech.rent.be.enums.Role;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);

    Users findUsersById(Long id);

    Users findUsersByRole(Role role);
}