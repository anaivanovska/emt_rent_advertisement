package mk.com.ukim.finki.rent_advertisement.init;

import mk.com.ukim.finki.rent_advertisement.domain.model.Role;
import mk.com.ukim.finki.rent_advertisement.domain.model.User;
import mk.com.ukim.finki.rent_advertisement.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitilizeAdmin {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Value("${admin.username}")
    private String username;
    @Value("${admin.password}")
    private String password;

    @PostConstruct
    public void init(){
        if(userRepository.count() == 0 ){
            User admin = new User();
            admin.setUsername(username);
            admin.setPassword(passwordEncoder.encode(password));
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
        }
    }
}
