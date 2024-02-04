package tech.rent.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.rent.be.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findBookingById(Long Id);
}
