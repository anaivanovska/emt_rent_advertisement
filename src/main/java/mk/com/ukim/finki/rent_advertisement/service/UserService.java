package mk.com.ukim.finki.rent_advertisement.service;

import mk.com.ukim.finki.rent_advertisement.domain.dto.PasswordDTO;
import mk.com.ukim.finki.rent_advertisement.domain.dto.UserDTO;
import mk.com.ukim.finki.rent_advertisement.domain.dto.UserRegistrationDTO;

public interface UserService {
     UserDTO createUser(UserRegistrationDTO userRegistrationDTO);
     UserDTO updateUser(UserDTO userDTO);
     void changeUserPassword(PasswordDTO passwordDTO);
     UserDTO getUser(String username);
     String resetPassword(String username);

}
