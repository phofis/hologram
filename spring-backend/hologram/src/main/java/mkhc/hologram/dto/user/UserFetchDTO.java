package mkhc.hologram.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mkhc.hologram.model.Picture;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
public class UserFetchDTO {
    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfBirth;
    private String phone;
    private Picture profilePicture;
}
