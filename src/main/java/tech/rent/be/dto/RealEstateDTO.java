package tech.rent.be.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tech.rent.be.entity.Booking;
import tech.rent.be.entity.Resource;
import tech.rent.be.entity.Users;
import tech.rent.be.enums.EstateStatus;
//import tech.rent.be.entity.Resource;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class RealEstateDTO {

    Long Id;
    String title;
    String description;
    // Date date;
    Long amount;
    Long Price;
    Long categoryId;
    Long locationId;
    String category;
    String location;
    Time checkIn;
    Time checkOut;

    List<ResourceDTO> resources;

    Users users;
    List<Booking> bookings;
    EstateStatus estateStatus;

}