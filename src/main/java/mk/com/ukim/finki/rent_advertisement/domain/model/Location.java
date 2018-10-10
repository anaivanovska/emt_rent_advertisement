package mk.com.ukim.finki.rent_advertisement.domain.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;


@Entity
@Table(name = "locations")
public class Location {
    @Id
    private Long id;
    private BigDecimal longitude;
    private BigDecimal latitude;
    @OneToOne(mappedBy = "location")
    private RentAdvertisement advertisement;
}
