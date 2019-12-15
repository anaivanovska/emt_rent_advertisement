package mk.com.ukim.finki.rent_advertisement.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDTO {
    private long id;
    private String title;
    private byte[] image;

    public ImageDTO(){

    }
}
