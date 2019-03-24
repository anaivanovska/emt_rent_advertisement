package mk.com.ukim.finki.rent_advertisement.persistence;

import mk.com.ukim.finki.rent_advertisement.domain.model.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long>{
     Location findLocationByLatitudeAndLongitude(BigDecimal latitude, BigDecimal longitude);
}
