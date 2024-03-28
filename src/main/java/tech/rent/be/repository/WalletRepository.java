package tech.rent.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.rent.be.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

}
