package cz.osu.itemrecordsbe.security.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
public class SignupRequest {
    @NotBlank
    @Size(min = 3)
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

}
