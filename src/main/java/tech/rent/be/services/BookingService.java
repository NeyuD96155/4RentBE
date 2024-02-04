package tech.rent.be.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.rent.be.dto.BookingRequestDTO;
import tech.rent.be.dto.RealEstateDTO;
import tech.rent.be.entity.Booking;
import tech.rent.be.entity.RealEstate;
import tech.rent.be.entity.Users;
import tech.rent.be.repository.BookingRepository;
import tech.rent.be.repository.PaymentRepository;
import tech.rent.be.repository.RealEstateRepository;
import tech.rent.be.repository.UsersRepository;
@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    RealEstateRepository realEstateRepository;

    @Autowired
    UsersRepository usersRepository;
    public Booking createBooking(BookingRequestDTO BookingRequestDTO){
        Users users = usersRepository.findUsersById(BookingRequestDTO.getUserId());
        RealEstate realEstate = realEstateRepository.findRealEstateById(BookingRequestDTO.getEstateId());
        Booking Booking = new Booking();
        Booking.setBookingDate(BookingRequestDTO.getBookingDate());
        Booking.setCheckIn(BookingRequestDTO.getCheckIn());
        Booking.setCheckOut(BookingRequestDTO.getCheckOut());
        Booking.setBookingDate(BookingRequestDTO.getBookingDate());
        Booking.setStatus(BookingRequestDTO.getStatus());
        Booking.setUsers(users);
        Booking.setRealEstate(realEstate);
        return bookingRepository.save(Booking);
    }
}
