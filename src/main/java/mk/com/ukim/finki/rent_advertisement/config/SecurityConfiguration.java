package mk.com.ukim.finki.rent_advertisement.config;


import mk.com.ukim.finki.rent_advertisement.security.JwtAuthenticationFilter;
import mk.com.ukim.finki.rent_advertisement.security.JwtAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, "login").permitAll()
                .antMatchers("/api/user/register").permitAll()
                .antMatchers("/api/user/get/**", "/api/user/update", "/api/user/changePassword")
                .hasAnyRole("ADMIN", "ADVERTISER")
                .and()
                .addFilter(new JwtAuthorizationFilter(authenticationManagerBean()))
                .addFilter(new JwtAuthenticationFilter(authenticationManagerBean())).exceptionHandling()
                .authenticationEntryPoint(
                        (req, resp, exc) -> resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"))
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
