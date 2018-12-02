package mk.com.ukim.finki.rent_advertisement;

import mk.com.ukim.finki.rent_advertisement.init.Admin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(Admin.class)
public class RentAdvertisementApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentAdvertisementApplication.class, args);
	}
}
