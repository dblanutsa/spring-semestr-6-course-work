package dmitriy.blanutsa.coursework.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
public class LoginRequest {

    @NotBlank
    private String loginOrEmail;

    @NotBlank
    private String password;
}
