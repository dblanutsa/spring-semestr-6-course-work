package dmitriy.blanutsa.coursework.services.impl;

import dmitriy.blanutsa.coursework.controller.dto.*;
import dmitriy.blanutsa.coursework.exception.AppException;
import dmitriy.blanutsa.coursework.persistance.entities.Role;
import dmitriy.blanutsa.coursework.persistance.entities.User;
import dmitriy.blanutsa.coursework.persistance.repositories.RoleRepository;
import dmitriy.blanutsa.coursework.persistance.repositories.UserRepository;
import dmitriy.blanutsa.coursework.security.JwtTokenProvider;
import dmitriy.blanutsa.coursework.security.RoleConstants;
import dmitriy.blanutsa.coursework.security.UserPrincipal;
import dmitriy.blanutsa.coursework.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider tokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository,
                           RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @Override
    @Transactional
    public ResponseEntity<?> signup(SignUpRequest signUpRequest) {
        if(userRepository.existsByLogin(signUpRequest.getLogin())) {
            return new ResponseEntity<>(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        User user = new User(signUpRequest.getLogin(), signUpRequest.getFirstName(),
                signUpRequest.getLastName(), signUpRequest.getEmail(), signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleConstants.BASE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));
  
        user.setRoles(Collections.singletonList(userRole));

        userRepository.saveAndFlush(user);

        return new ResponseEntity<>(new ApiResponse(true, "Username successfuly registered!"),
                HttpStatus.OK);
    }

    @Override
    public TokenResponse signin(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getLoginOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new TokenResponse(tokenProvider.generateToken(authentication));
    }

    @Override
    @Transactional
    public boolean checkLoginUnique(String login) {
        return !userRepository.existsByLogin(login);
    }

    @Override
    @Transactional
    public boolean checkEmailUnique(String email) {
        return !userRepository.existsByEmail(email);
    }
}
