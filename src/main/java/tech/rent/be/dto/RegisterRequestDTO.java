package tech.rent.be.dto;

import lombok.Data;
import tech.rent.be.enums.Role;

@Data

public class RegisterRequestDTO {
    String username;
    String password;
    String email;
    Role role;
}
