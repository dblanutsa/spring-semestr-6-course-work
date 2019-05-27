package dmitriy.blanutsa.coursework.controller;

import dmitriy.blanutsa.coursework.controller.dto.*;
import dmitriy.blanutsa.coursework.security.UserPrincipal;
import dmitriy.blanutsa.coursework.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
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

    @GetMapping("/checkLogin")
    public ApiResponse checkLoginUnique(@RequestParam(value = "login") String login) {
        return new ApiResponse(authService.checkLoginUnique(login));
    }

    @GetMapping("/checkEmail")
    public ApiResponse checkEmailUnique(@RequestParam(value = "email") String email) {
        return new ApiResponse(authService.checkEmailUnique(email));
    }
}
