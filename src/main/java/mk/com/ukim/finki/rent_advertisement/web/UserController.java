package mk.com.ukim.finki.rent_advertisement.web;

import mk.com.ukim.finki.rent_advertisement.domain.dto.PasswordDTO;
import mk.com.ukim.finki.rent_advertisement.domain.dto.UserDTO;
import mk.com.ukim.finki.rent_advertisement.domain.dto.UserRegistrationDTO;
import mk.com.ukim.finki.rent_advertisement.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/register")
    public UserDTO createNewUser(@RequestBody UserRegistrationDTO userRegistrationDTO){
        UserDTO userDTO = userService.createUser(userRegistrationDTO);
        return userDTO;
    }

    @PostMapping("/update")
    public UserDTO updateUser(@RequestBody UserDTO userDTO){
        UserDTO updatedUserDTO = userService.updateUser(userDTO);
        return updatedUserDTO;
    }

    @PostMapping("/changePassword")
    public ResponseEntity<Void> changeUserPassword(@RequestBody PasswordDTO passwordDTO){
        userService.changeUserPassword(passwordDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get/{username}")
    public UserDTO getUser(@PathVariable String username){
        UserDTO result = userService.getUser(username);
        return result;
    }
}
