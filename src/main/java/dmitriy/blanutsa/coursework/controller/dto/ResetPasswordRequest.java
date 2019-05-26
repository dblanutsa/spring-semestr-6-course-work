package dmitriy.blanutsa.coursework.controller.dto;

import lombok.Getter;

@Getter
public class ResetPasswordRequest {

    private String oldPassword;
    private String newPassword;
}
