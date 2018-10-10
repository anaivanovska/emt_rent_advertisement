package mk.com.ukim.finki.rent_advertisement.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.web.ResourceProperties;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="rent_advertisement")
@Getter
@Setter
public class RentAdvertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private LocalDateTime advertisementShareDate;
    @ManyToOne
    private User user;
    @OneToOne
    private Location location;

    public RentAdvertisement(){

    }

}
