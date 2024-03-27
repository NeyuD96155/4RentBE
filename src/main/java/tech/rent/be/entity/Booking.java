package tech.rent.be.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import tech.rent.be.enums.BookingStatus;
import tech.rent.be.enums.EstateStatus;

import java.util.Date;

@Entity
@Getter
@Setter
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    
    Date bookingDate;
    Boolean Status;
    Long price;
    int amount;

    @Enumerated(EnumType.STRING)
    BookingStatus bookingStatus;

    @ManyToOne
    @JoinColumn(name = "user_id")
    Users users;

    @JsonIgnore
    @OneToOne(mappedBy = "booking")
    Payment payment;


    @ManyToOne
    @JoinColumn(name = "estate_id", unique = false)
    @JsonIgnore
    RealEstate realEstate;
}