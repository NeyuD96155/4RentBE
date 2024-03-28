package tech.rent.be.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Date createAt = new Date();
    float value;

    @ManyToOne
    @JoinColumn(name = "from_id")
    Wallet from;

    @ManyToOne
    @JoinColumn(name = "to_id")
    Wallet to;
}
