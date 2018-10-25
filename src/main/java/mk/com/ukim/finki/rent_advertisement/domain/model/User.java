package mk.com.ukim.finki.rent_advertisement.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {
    @Id
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Gender gender;
    private Role role;
    private Boolean active;
    @OneToMany(mappedBy = "user")
    private Set<RentAdvertisement> advertisements;

    public User(){

    }

}
