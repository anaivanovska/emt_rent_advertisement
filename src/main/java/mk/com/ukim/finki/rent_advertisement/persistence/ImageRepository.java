package mk.com.ukim.finki.rent_advertisement.persistence;

import mk.com.ukim.finki.rent_advertisement.domain.model.Image;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {
    List<Image> findAllByStorageRentAd_Id(long id);
}
