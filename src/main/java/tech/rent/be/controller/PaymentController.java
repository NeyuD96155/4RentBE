package tech.rent.be.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.rent.be.dto.BookingRequestDTO;
import tech.rent.be.dto.PaymentDTO;
import tech.rent.be.entity.Booking;
import tech.rent.be.entity.Payment;
import tech.rent.be.services.PaymentService;

@RestController
@CrossOrigin
public class PaymentController {
    @Autowired
    PaymentService paymentService;
    @PostMapping("/Payment")
    public ResponseEntity payment(@RequestBody PaymentDTO paymentDTO){
        Payment payment = paymentService.createPayment(paymentDTO);
        return  ResponseEntity.ok(paymentDTO);
    }

}
