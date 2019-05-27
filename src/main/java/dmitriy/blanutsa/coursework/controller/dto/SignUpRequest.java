package dmitriy.blanutsa.coursework.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Getter
@Setter
public class SignUpRequest {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_]{6,20}$")
    private String login;

    @Size(max = 20)
    private String firstName;

    @Size(max = 20)
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^\\S{6,20}$")
    private String password;
}
