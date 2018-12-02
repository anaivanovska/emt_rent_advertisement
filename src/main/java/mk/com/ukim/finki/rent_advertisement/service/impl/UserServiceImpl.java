package mk.com.ukim.finki.rent_advertisement.service.impl;

import mk.com.ukim.finki.rent_advertisement.domain.Constants.Constants;
import mk.com.ukim.finki.rent_advertisement.domain.dto.PasswordDTO;
import mk.com.ukim.finki.rent_advertisement.domain.dto.UserDTO;
import mk.com.ukim.finki.rent_advertisement.domain.dto.UserRegistrationDTO;
import mk.com.ukim.finki.rent_advertisement.domain.exceptions.StorageRentAdException;
import mk.com.ukim.finki.rent_advertisement.domain.model.Role;
import mk.com.ukim.finki.rent_advertisement.domain.model.User;
import mk.com.ukim.finki.rent_advertisement.persistence.UserRepository;
import mk.com.ukim.finki.rent_advertisement.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JavaMailSender mailSender;
    @Value("${mail.from}")
    private String fromMail;

    @Transactional
    @Override
    public UserDTO createUser(UserRegistrationDTO userRegistrationDTO) {
        User userInDB = this.userRepository.findById(userRegistrationDTO.getUsername()).orElse(null);
        if(userInDB != null){
            throw new StorageRentAdException("User with username: "+ userRegistrationDTO.getUsername() + " already exists.");
        }
        User user = modelMapper.map(userRegistrationDTO, User.class);
        user.setRole(Role.ADVERTISER);
        user.setActive(false);
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        user = userRepository.save(user);
        UserDTO result = modelMapper.map(user, UserDTO.class);
        return result;

    }

    @Transactional
    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        User user = findUserbyUsername(userDTO.getUsername());
        if(!userDTO.getFirstName().trim().equals("")) {
            user.setFirstName(userDTO.getFirstName());
        }
        if(!userDTO.getLastName().trim().equals("")) {
            user.setLastName(userDTO.getLastName());
        }
        if(!userDTO.getEmail().trim().equals("")) {
            user.setEmail(userDTO.getEmail());
        }
        if(!userDTO.getPhoneNumber().trim().equals("")) {
            user.setPhoneNumber(userDTO.getPhoneNumber());
        }
        if(!userDTO.getGender().toString().trim().equals("")) {
            user.setGender(userDTO.getGender());
        }
        user = userRepository.save(user);
        UserDTO resultDTO = modelMapper.map(user, UserDTO.class);
        return resultDTO;
    }

    @Transactional
    @Override
    public void changeUserPassword(PasswordDTO passwordDTO) {
        User user = findUserbyUsername(passwordDTO.getUsername());
        if(!passwordEncoder.matches(passwordDTO.getPassword(), user.getPassword())){
            throw new StorageRentAdException("Wrong password");
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
                .orElseThrow(() -> new StorageRentAdException("User - " + username + " not found"));
        return user;
    }

    @Transactional
    @Override
    public String resetPassword(String username){
        User user = findUserbyUsername(username);
        String newPassword = UUID.randomUUID().toString();
        user.setPassword(passwordEncoder.encode(newPassword));
        user = userRepository.save(user);
        sendEmail(Constants.RESET_PASSWORD_EMAIL_SUBJECT, Constants.RESET_PASSWORD_EMAIL_TEXT+" PASSWORD: " + newPassword, user.getEmail());
        return user.getEmail();
    }

    private void sendEmail(String subject, String messageBody, String toMail){
        SimpleMailMessage message =  new SimpleMailMessage();
        message.setSubject(subject);
        message.setText(messageBody);
        message.setTo(toMail);
        message.setFrom(fromMail);
        mailSender.send(message);
    }
}
