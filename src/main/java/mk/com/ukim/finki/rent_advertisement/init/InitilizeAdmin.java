package mk.com.ukim.finki.rent_advertisement.init;

import mk.com.ukim.finki.rent_advertisement.domain.model.User;
import mk.com.ukim.finki.rent_advertisement.persistence.UserRepository;
import mk.com.ukim.finki.rent_advertisement.init.Admin;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;

@Component
public class InitilizeAdmin {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private Admin admin;
    @Autowired
    private ModelMapper modelMapper;


    @PostConstruct
    public void init(){
        if(userRepository.count() == 0 ){
            User user = new User();
            user.setUsername(admin.getUsername());
            user.setPassword(passwordEncoder.encode(admin.getPassword()));
            user.setFirstName(admin.getFirstName());
            user.setLastName(admin.getLastName());
            user.setGender(admin.getGender());
            user.setEmail(admin.getEmail());
            user.setPhoneNumber(admin.getPhoneNumber());
            user.setRole(admin.getRole());
            user.setActive(true);
            userRepository.save(user);
        }
    }
}
