package mk.com.ukim.finki.rent_advertisement.domain.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageDTO {
    private String title;
    private String description;
    private byte[] image;

    public ImageDTO(){

    }
}
