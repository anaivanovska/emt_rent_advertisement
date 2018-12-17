package mk.com.ukim.finki.rent_advertisement.persistence;

import mk.com.ukim.finki.rent_advertisement.domain.model.StorageRentAd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface StorageRentAdRepository extends CrudRepository<StorageRentAd, Long>{
    Page<StorageRentAd> findAllByPublisher_Username(String username, Pageable pageable);
    Page<StorageRentAd> findAll(Pageable pageable);
}
