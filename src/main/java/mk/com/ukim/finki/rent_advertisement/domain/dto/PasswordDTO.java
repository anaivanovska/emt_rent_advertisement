package mk.com.ukim.finki.rent_advertisement.domain.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordDTO {
    private String username;
    private String password;
    private String newPassword;
    private String  confirmNewPassword;

    public PasswordDTO(){

    }
}
