package mk.com.ukim.finki.rent_advertisement.security;

import mk.com.ukim.finki.rent_advertisement.domain.model.User;
import mk.com.ukim.finki.rent_advertisement.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findById(username).orElseThrow(() ->
                new UsernameNotFoundException("User with username: "+username +" not found"));
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_"+user.getRole().toString());
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
    }
}
