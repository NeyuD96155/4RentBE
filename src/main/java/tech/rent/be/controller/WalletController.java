package tech.rent.be.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.rent.be.entity.Transactions;
import tech.rent.be.entity.Users;
import tech.rent.be.entity.Wallet;
import tech.rent.be.repository.TransactionRepository;
import tech.rent.be.utils.AccountUtils;

import java.util.Comparator;

@RestController
@CrossOrigin
@SecurityRequirement(name = "api")
public class WalletController {

    @Autowired
    AccountUtils accountUtils;

    @Autowired
    TransactionRepository transactionRepository;

    @GetMapping("/wallet")
    public ResponseEntity getWallet(){
        Users users = accountUtils.getCurrentUser();
        return ResponseEntity.ok(users.getWallet());
    }

   
}
