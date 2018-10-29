package mk.com.ukim.finki.rent_advertisement.domain.dto;

import lombok.Getter;
import lombok.Setter;
import mk.com.ukim.finki.rent_advertisement.domain.model.AdvertisementStatus;

@Getter
@Setter
public class RentAdvertisementDTO {
    private String title;
    private String description;
    private String creationDate;
    private AdvertisementStatus status;
    private LocationDTO storageLocation;

    public RentAdvertisementDTO() {

    }
}
