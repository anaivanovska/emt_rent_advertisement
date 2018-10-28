package mk.com.ukim.finki.rent_advertisement.web;

import mk.com.ukim.finki.rent_advertisement.domain.Constants.Constants;
import mk.com.ukim.finki.rent_advertisement.domain.dto.PasswordDTO;
import mk.com.ukim.finki.rent_advertisement.domain.dto.UserCredentials;
import mk.com.ukim.finki.rent_advertisement.domain.dto.UserDTO;
import mk.com.ukim.finki.rent_advertisement.domain.dto.UserRegistrationDTO;
import mk.com.ukim.finki.rent_advertisement.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/")
@CrossOrigin(origins = Constants.ALLOWED_ORIGINS, allowedHeaders = Constants.ALLOWED_RESPONSE_HEADERS, exposedHeaders = Constants.EXPOSED_HEADERS)
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("login")
    public void loginUser(@RequestBody UserCredentials credentials){

    }
    @PostMapping("api/user/register")
    public UserDTO createNewUser(@RequestBody UserRegistrationDTO userRegistrationDTO){
        UserDTO userDTO = userService.createUser(userRegistrationDTO);
        return userDTO;
    }

    @PostMapping("api/user/update")
    public UserDTO updateUser(@RequestBody UserDTO userDTO){
        UserDTO updatedUserDTO = userService.updateUser(userDTO);
        return updatedUserDTO;
    }

    @PostMapping("api/user/changePassword")
    public ResponseEntity<Void> changeUserPassword(@RequestBody PasswordDTO passwordDTO){
        userService.changeUserPassword(passwordDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("api/user/get/{username}")
    public UserDTO getUser(@PathVariable String username){
        UserDTO result = userService.getUser(username);
        return result;
    }
}
