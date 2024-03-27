package tech.rent.be.dto;

import lombok.Data;
import tech.rent.be.entity.RealEstate;

import java.util.Date;
import java.util.List;
@Data
public class PostResponseDTO {
    Long id;
    String content;
    String title;
    Long discount;
    RealEstate realEstate;
}
