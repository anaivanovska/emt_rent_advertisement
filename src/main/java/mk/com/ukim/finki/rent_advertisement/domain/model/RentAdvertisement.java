package mk.com.ukim.finki.rent_advertisement.domain.model;

import lombok.Getter;
import lombok.Setter;
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
    private LocalDateTime creationDate;
    private AdvertisementStatus status;
    @ManyToOne
    private User user;
    @ManyToOne
    private Location storageLocation;

    public RentAdvertisement(){

    }

}
