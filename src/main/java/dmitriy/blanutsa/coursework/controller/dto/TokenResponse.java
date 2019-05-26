package dmitriy.blanutsa.coursework.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponse {

    private String token;
    private String type = "Bearer ";

    public TokenResponse(String token) {
        this.token = token;
    }
}
