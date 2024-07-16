package mkhc.hologram.dto.user;

import lombok.Getter;
import lombok.Setter;
import mkhc.hologram.model.User;

import java.sql.Date;

@Getter
@Setter
public class UserRegisterDTO {
    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfBirth;
    private String password;
    private String phone;

    public User toModel(){
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setDateOfBirth(dateOfBirth);
        user.setPassword(password);
        user.setPhoneNumber(phone);
        return user;
    }
}
