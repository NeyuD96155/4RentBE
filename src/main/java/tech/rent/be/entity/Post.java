package tech.rent.be.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.rent.be.enums.PostStatus;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(columnDefinition = "nvarchar(255)")
    String content;

    @Column(columnDefinition = "nvarchar(255)")
    String title;

    Long discount;
    @Enumerated(EnumType.STRING)
    private PostStatus postStatus;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    Users users;

    @ManyToOne
    @JoinColumn(name = "realEstate_id")
    RealEstate realEstate;

}