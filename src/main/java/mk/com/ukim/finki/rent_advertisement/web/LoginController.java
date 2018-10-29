package mk.com.ukim.finki.rent_advertisement.web;

import mk.com.ukim.finki.rent_advertisement.domain.Constants.SecurityConstants;
import mk.com.ukim.finki.rent_advertisement.domain.dto.UserCredentials;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = SecurityConstants.ALLOWED_ORIGINS, allowedHeaders = SecurityConstants.ALLOWED_HEADERS, exposedHeaders = SecurityConstants.EXPOSED_HEADERS)
public class LoginController {

    @PostMapping("l6ogin")
    public void loginUser(@RequestBody UserCredentials credentials){

    }
}
