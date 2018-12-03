package mk.com.ukim.finki.rent_advertisement.domain.dto;

import lombok.Getter;
import lombok.Setter;
import mk.com.ukim.finki.rent_advertisement.domain.model.AdvertisementStatus;

import java.util.Set;

@Getter
@Setter
public class StorageRentAdDTO {
    private long id;
    private String title;
    private String description;
    private String creationDate;
    private AdvertisementStatus status;
    private LocationDTO storageLocation;
    private Set<ImageDTO> images;

    public StorageRentAdDTO() {

    }
}
