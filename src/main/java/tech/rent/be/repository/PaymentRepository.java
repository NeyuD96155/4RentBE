package tech.rent.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.rent.be.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
