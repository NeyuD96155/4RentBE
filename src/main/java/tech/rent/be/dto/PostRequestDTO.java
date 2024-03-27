package tech.rent.be.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PostRequestDTO {
    Long id;
    String content;
    String title;
    Long discount;
    Long estateId;
}