package mk.com.ukim.finki.rent_advertisement.persistence;

import mk.com.ukim.finki.rent_advertisement.domain.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
