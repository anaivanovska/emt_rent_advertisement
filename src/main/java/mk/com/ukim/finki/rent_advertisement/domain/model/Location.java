package mk.com.ukim.finki.rent_advertisement.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;


@Entity
@Table(name = "locations")
@Getter
@Setter
public class Location {
    @Id
    private Long id;
    private BigDecimal longitude;
    private BigDecimal latitude;
    @OneToMany(mappedBy = "storageLocation")
    private Set<RentAdvertisement> advertisement;

    public Location(){

    }
}
