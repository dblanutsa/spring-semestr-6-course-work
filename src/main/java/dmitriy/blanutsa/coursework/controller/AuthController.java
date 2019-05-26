package dmitriy.blanutsa.coursework.controller;

import dmitriy.blanutsa.coursework.controller.dto.*;
import dmitriy.blanutsa.coursework.security.UserPrincipal;
import dmitriy.blanutsa.coursework.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    public TokenResponse signin(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.signin(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignUpRequest signUpRequest) {
        return authService.signup(signUpRequest);
    }

    @PostMapping("/resetPassword")
    @ResponseStatus(HttpStatus.OK)
    public void resetPassword(@AuthenticationPrincipal UserPrincipal currentUser,
                                           @Valid @RequestBody ResetPasswordRequest resetPasswordRequest) {
        authService.resetPassword(currentUser, resetPasswordRequest);
    }

    @GetMapping("/checkLoginUnique")
    public ApiResponse checkLoginUnique(@RequestParam(value = "login") String login) {
        return new ApiResponse(authService.checkLoginUnique(login));
    }

    @GetMapping("/checkEmailUnique")
    public ApiResponse checkEmailUnique(@RequestParam(value = "email") String email) {
        return new ApiResponse(authService.checkEmailUnique(email));
    }
}
