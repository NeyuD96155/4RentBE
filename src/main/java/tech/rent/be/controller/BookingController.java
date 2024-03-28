package tech.rent.be.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.rent.be.dto.BookingRequestDTO;
import tech.rent.be.dto.BookingResponse;
import tech.rent.be.dto.PaymentDTO;
import tech.rent.be.entity.Booking;
import tech.rent.be.entity.Payment;
import tech.rent.be.entity.RealEstate;
import tech.rent.be.services.BookingService;
import tech.rent.be.services.PaymentService;

import java.util.List;

@RestController
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class BookingController {
    @Autowired
    PaymentService paymentService;

    @Autowired
    BookingService bookingService;

    @PostMapping("/vn-pay")
    public ResponseEntity createOrder(@RequestBody BookingRequestDTO bookingRequestDTO) throws Exception {
        String url = bookingService.getVnPay(bookingRequestDTO);
        return  ResponseEntity.ok(url);
    }
    @GetMapping("/success")
    public ResponseEntity<Booking> update(@RequestParam long vnp_TxnRef){
        Booking booking = bookingService.updatePayment(vnp_TxnRef);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/showBookingHistory")
    public ResponseEntity<List<BookingResponse>> getAllRealEstate() {
        List<BookingResponse> booking = paymentService.getAllBooking();
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/showBookingOfMember")
    public ResponseEntity<List<Booking>> getAllMemberBooking() {
        List<Booking> booking = paymentService.getAllBookingOfMember();
        return ResponseEntity.ok(booking);
    }

    @PutMapping("/cancelBooking/{bookingId}")
    public ResponseEntity<Booking> cancelBooking(@PathVariable Long bookingId){
        Booking booking = bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok(booking);
    }

    @PutMapping("/finishBooking/{bookingId}")
    public ResponseEntity<Booking> finishBooking(@PathVariable Long bookingId){
        Booking booking = bookingService.finishBooking(bookingId);
        return ResponseEntity.ok(booking);
    }

}
