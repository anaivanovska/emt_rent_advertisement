package mk.com.ukim.finki.rent_advertisement.service.impl;

import mk.com.ukim.finki.rent_advertisement.domain.dto.PasswordDTO;
import mk.com.ukim.finki.rent_advertisement.domain.dto.UserDTO;
import mk.com.ukim.finki.rent_advertisement.domain.dto.UserRegistrationDTO;
import mk.com.ukim.finki.rent_advertisement.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserDTO createUser(UserRegistrationDTO userRegistrationDTO) {
        return null;
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        return null;
    }

    @Override
    public void changeUserPassword(PasswordDTO passwordDTO) {

    }
}
