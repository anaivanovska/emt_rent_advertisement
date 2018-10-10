package mk.com.ukim.finki.rent_advertisement.persistence;

import mk.com.ukim.finki.rent_advertisement.domain.model.RentAdvertisement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentAdvertisementRepository extends CrudRepository<RentAdvertisement, Long>{
}
