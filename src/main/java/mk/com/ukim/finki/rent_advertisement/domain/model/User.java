package mk.com.ukim.finki.rent_advertisement.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.*;

import javax.persistence.*;
import java.util.List;

@Indexed
@Entity
@Table(name = "user")
@Getter
@Setter
public class User {
    @Id
    @DocumentId
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Gender gender;
    private Role role;
    private Boolean active;
    @ContainedIn
    @OneToMany(mappedBy = "publisher")
    private List<StorageRentAd> storageRentAds;

    public User(){

    }

}
