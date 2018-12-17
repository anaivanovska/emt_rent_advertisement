package mk.com.ukim.finki.rent_advertisement.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.util.Set;


@Entity
@Table(name = "locations")
@Getter
@Setter
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Digits(integer = 10, fraction = 10)
    private BigDecimal longitude;
    @Digits(integer = 10, fraction = 10)
    private BigDecimal latitude;
    @OneToMany(mappedBy = "storageLocation")
    private Set<StorageRentAd> storageRentAds;

    public Location(){

    }
}
