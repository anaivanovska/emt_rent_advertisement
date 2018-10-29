package mk.com.ukim.finki.rent_advertisement.domain.dto;

import lombok.Getter;
import lombok.Setter;
import mk.com.ukim.finki.rent_advertisement.domain.model.Gender;
import mk.com.ukim.finki.rent_advertisement.domain.model.RentAdvertisement;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class UserDTO implements Serializable{
    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Gender gender;
    private Set<RentAdvertisement> advertisements;

    public UserDTO(){

    }
}
