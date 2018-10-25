package mk.com.ukim.finki.rent_advertisement.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class RentAdvertisementException extends RuntimeException{
    public RentAdvertisementException(String message){
        super(message);
    }
}
