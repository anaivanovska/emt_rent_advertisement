package mk.com.ukim.finki.rent_advertisement.service.impl;

import mk.com.ukim.finki.rent_advertisement.domain.dto.PasswordDTO;
import mk.com.ukim.finki.rent_advertisement.domain.dto.UserDTO;
import mk.com.ukim.finki.rent_advertisement.domain.dto.UserRegistrationDTO;
import mk.com.ukim.finki.rent_advertisement.domain.exceptions.RentAdvertisementException;
import mk.com.ukim.finki.rent_advertisement.domain.model.RentAdvertisement;
import mk.com.ukim.finki.rent_advertisement.domain.model.Role;
import mk.com.ukim.finki.rent_advertisement.domain.model.User;
import mk.com.ukim.finki.rent_advertisement.persistence.UserRepository;
import mk.com.ukim.finki.rent_advertisement.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserDTO createUser(UserRegistrationDTO userRegistrationDTO) {
        User user = modelMapper.map(userRegistrationDTO, User.class);
        user.setRole(Role.ADVERTISER);
        user.setActive(false);
        user = userRepository.save(user);
        UserDTO result = modelMapper.map(user, UserDTO.class);
        return result;

    }

    @Transactional
    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User user = findUserbyUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setGender(userDTO.getGender());
        user = userRepository.save(user);
        UserDTO resultDTO = modelMapper.map(user, UserDTO.class);
        return resultDTO;
    }

    @Transactional
    @Override
    public void changeUserPassword(PasswordDTO passwordDTO) {
        User user = findUserbyUsername(passwordDTO.getUsername());
        if(!passwordEncoder.matches(passwordDTO.getPassword(), user.getPassword())){
            throw new RentAdvertisementException("Wrong password");
        }

        user.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
        user = userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public UserDTO getUser(String username) {
        User user = findUserbyUsername(username);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

    private User findUserbyUsername(String username){
        User user = userRepository.findById(username)
                .orElseThrow(() -> new RentAdvertisementException("User - " + username + " not found"));
        return user;
    }
}
