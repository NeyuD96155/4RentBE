package tech.rent.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.rent.be.entity.Transactions;
import tech.rent.be.entity.Wallet;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {

    List<Transactions> findTransactionssByFromOrTo(Wallet from, Wallet to);

}
