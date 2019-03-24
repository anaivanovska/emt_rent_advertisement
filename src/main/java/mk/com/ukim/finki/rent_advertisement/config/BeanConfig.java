package mk.com.ukim.finki.rent_advertisement.config;

import mk.com.ukim.finki.rent_advertisement.domain.dto.UserDTO;
import mk.com.ukim.finki.rent_advertisement.domain.model.Location;
import mk.com.ukim.finki.rent_advertisement.domain.model.User;
import org.modelmapper.*;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class BeanConfig {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(new Converter<LocalDateTime, String>() {
            @Override
            public String convert(MappingContext<LocalDateTime, String> context) {
                return context.getSource() == null ? "" : context.getSource().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
            }
        });

        return modelMapper;
    }
}
