package tech.rent.be.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    @Column(columnDefinition = "nvarchar(255)")
    String Categoryname;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    List<RealEstate> estates;

}
