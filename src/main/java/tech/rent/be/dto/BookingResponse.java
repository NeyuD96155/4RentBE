package tech.rent.be.dto;


import lombok.Data;
import tech.rent.be.entity.RealEstate;
import tech.rent.be.entity.Users;
import tech.rent.be.enums.BookingStatus;

import java.util.Date;

@Data
public class BookingResponse {
    Long Id;
    Date checkOut;
    Date checkIn;
    Date bookingDate;
    Boolean Status;
    Long price;
    int amount;
    BookingStatus bookingStatus;
    Users users;
    RealEstate realEstate;
}