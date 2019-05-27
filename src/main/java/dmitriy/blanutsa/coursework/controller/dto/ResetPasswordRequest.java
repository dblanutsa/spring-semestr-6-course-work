package dmitriy.blanutsa.coursework.controller.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class ResetPasswordRequest {

    @NotBlank
    @Pattern(regexp = "^\\S{6,20}$")
    private String oldPassword;

    @NotBlank
    @Pattern(regexp = "^\\S{6,20}$")
    private String newPassword;
}
