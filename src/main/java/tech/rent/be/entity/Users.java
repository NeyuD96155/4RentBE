package tech.rent.be.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import tech.rent.be.enums.Role;

@Entity
@Getter
@Setter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    Role role;

    String username;
    String password;
    String email;
}
