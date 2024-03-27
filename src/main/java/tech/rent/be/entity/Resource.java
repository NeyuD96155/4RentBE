package tech.rent.be.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import tech.rent.be.enums.Type;

@Entity
@Data
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    Type resourceType;
    String url;

    @ManyToOne
    @JoinColumn(name = "estate_id")
    @JsonIgnore
    RealEstate realEstate;
}
