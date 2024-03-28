package tech.rent.be.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    float balance = 0;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    Users users;

    @OneToMany(mappedBy = "to", cascade = CascadeType.ALL)
    @JsonIgnore
    List<Transactions> payFor;

    @OneToMany(mappedBy = "from", cascade = CascadeType.ALL)
    @JsonIgnore
    List<Transactions> receiveFrom;
}
