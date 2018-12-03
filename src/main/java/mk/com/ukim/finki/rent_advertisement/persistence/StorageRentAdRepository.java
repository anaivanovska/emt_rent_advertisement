package mk.com.ukim.finki.rent_advertisement.persistence;

import mk.com.ukim.finki.rent_advertisement.domain.model.StorageRentAd;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StorageRentAdRepository extends CrudRepository<StorageRentAd, Long>{
    Set<StorageRentAd> findAllByUser_Username(String username);
}
