package mk.com.ukim.finki.rent_advertisement.service;

import mk.com.ukim.finki.rent_advertisement.domain.dto.PasswordDTO;
import mk.com.ukim.finki.rent_advertisement.domain.dto.UserDTO;
import mk.com.ukim.finki.rent_advertisement.domain.dto.UserRegistrationDTO;

public interface UserService {
    public UserDTO createUser(UserRegistrationDTO userRegistrationDTO);
    public UserDTO updateUser(UserDTO userDTO);
    public void changeUserPassword(PasswordDTO passwordDTO);
}
