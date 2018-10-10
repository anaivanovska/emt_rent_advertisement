package mk.com.ukim.finki.rent_advertisement.persistence;

import mk.com.ukim.finki.rent_advertisement.domain.model.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long>{
}
