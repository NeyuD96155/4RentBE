package tech.rent.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.rent.be.dto.BookingRequestDTO;
import tech.rent.be.entity.Booking;
import tech.rent.be.services.BookingService;

@RestController
@CrossOrigin
public class BookingController {
    @Autowired
    BookingService bookingService;
    @PostMapping("/Booking")
    public ResponseEntity booking(@RequestBody BookingRequestDTO bookingRequestDTO){
        Booking booking = bookingService.createBooking(bookingRequestDTO);
        return  ResponseEntity.ok(booking);
    }
}
