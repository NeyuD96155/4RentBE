package tech.rent.be.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.rent.be.entity.Users;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetail {
    private Users user;
    private String subject;

}
