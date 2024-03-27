package tech.rent.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.rent.be.entity.Booking;
import tech.rent.be.entity.RealEstate;
import tech.rent.be.entity.Users;

import java.util.Date;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findBookingById(Long Id);
    List<Booking> findBookingsByRealEstateAndBookingDateBetween(RealEstate realEstate, Date from, Date to);
    List<Booking> findBookingsByRealEstate(RealEstate realEstate);
    List<Booking> findBookingsByUsers(Users users);
}
