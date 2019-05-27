package dmitriy.blanutsa.coursework.services;

import dmitriy.blanutsa.coursework.controller.dto.LoginRequest;
import dmitriy.blanutsa.coursework.controller.dto.ResetPasswordRequest;
import dmitriy.blanutsa.coursework.controller.dto.SignUpRequest;
import dmitriy.blanutsa.coursework.controller.dto.TokenResponse;
import dmitriy.blanutsa.coursework.security.UserPrincipal;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<?> signup(SignUpRequest signUpRequest);

    TokenResponse signin(LoginRequest loginRequest);

    boolean checkLoginUnique(String login);

    boolean checkEmailUnique(String email);
}
