package tech.rent.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.rent.be.dto.BookingRequestDTO;
import tech.rent.be.dto.PaymentDTO;
import tech.rent.be.entity.Booking;
import tech.rent.be.entity.Payment;
import tech.rent.be.entity.RealEstate;
import tech.rent.be.entity.Users;
import tech.rent.be.repository.BookingRepository;
import tech.rent.be.repository.PaymentRepository;
@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    BookingRepository bookingRepository;
    public Payment createPayment(PaymentDTO paymentDTO){
        Booking booking = bookingRepository.findBookingById(paymentDTO.getBookingId());
        Payment payment = new Payment();
        payment.setName(paymentDTO.getEstateName());
        payment.setPrice(paymentDTO.getPrice());

        payment.setBooking(booking);

        return paymentRepository.save(payment);
    }
}
