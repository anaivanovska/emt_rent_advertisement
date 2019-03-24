package mk.com.ukim.finki.rent_advertisement.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Getter
@Setter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    @Lob
    private byte[] image;
    @ManyToOne
    private StorageRentAd storageRentAd;
    public Image(){

    }
}
