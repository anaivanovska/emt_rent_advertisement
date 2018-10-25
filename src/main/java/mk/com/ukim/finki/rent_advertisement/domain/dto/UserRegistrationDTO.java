package mk.com.ukim.finki.rent_advertisement.domain.dto;

import lombok.Getter;
import lombok.Setter;
import mk.com.ukim.finki.rent_advertisement.domain.model.Gender;

@Getter
@Setter
public class UserRegistrationDTO {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Gender gender;

    public UserRegistrationDTO(){

    }
}
