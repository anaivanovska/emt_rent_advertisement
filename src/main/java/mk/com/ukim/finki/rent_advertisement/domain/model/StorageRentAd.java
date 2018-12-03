package mk.com.ukim.finki.rent_advertisement.domain.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="storage_rent_ads")
@Getter
@Setter
public class StorageRentAd {
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
    @OneToMany
    private Set<Image> images;

    public StorageRentAd(){

    }

}
