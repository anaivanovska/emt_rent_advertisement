package mk.com.ukim.finki.rent_advertisement.init;

import lombok.Getter;
import lombok.Setter;
import mk.com.ukim.finki.rent_advertisement.domain.model.User;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "admin" )
public class Admin extends User{

    public Admin() {
        super();
    }
}
