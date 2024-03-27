package tech.rent.be.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.catalina.User;
import tech.rent.be.enums.EstateStatus;
import tech.rent.be.enums.PostStatus;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RealEstate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(columnDefinition = "nvarchar(255)")
    String title;
    @Column(columnDefinition = "nvarchar(10000)")
    String description;
    Date date;
    Long amount;

    Long price;

    Time checkIn;
    Time checkOut;

}