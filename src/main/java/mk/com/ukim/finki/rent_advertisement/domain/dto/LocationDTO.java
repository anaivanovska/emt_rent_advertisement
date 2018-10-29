package mk.com.ukim.finki.rent_advertisement.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class LocationDTO {
    private BigDecimal latitude;
    private BigDecimal longitude;

    public LocationDTO() {

    }
}
