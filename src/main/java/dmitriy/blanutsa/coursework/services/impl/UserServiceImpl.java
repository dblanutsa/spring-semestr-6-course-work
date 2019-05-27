package dmitriy.blanutsa.coursework.services.impl;

import dmitriy.blanutsa.coursework.controller.dto.ResetPasswordRequest;
import dmitriy.blanutsa.coursework.controller.dto.UserProfile;
import dmitriy.blanutsa.coursework.exception.AppException;
import dmitriy.blanutsa.coursework.exception.ResourceNotFoundException;
import dmitriy.blanutsa.coursework.persistance.entities.User;
import dmitriy.blanutsa.coursework.persistance.repositories.UserRepository;
import dmitriy.blanutsa.coursework.security.UserPrincipal;
import dmitriy.blanutsa.coursework.services.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserProfile getProfile(UserPrincipal principal) {
        return UserProfile.valueOf(principal);
    }

    @Override
    @Transactional
    public UserProfile updateProfile(UserProfile profile) {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        User user = userRepository.findByLogin(principal.getLogin());
        user.setFirstName(profile.getFirstName());
        user.setLastName(profile.getLastName());
        user.setEmail(profile.getEmail());

        return UserProfile.valueOf(updateSecurityContext(user));
    }

    @Override
    @Transactional
    public void resetPassword(ResetPasswordRequest request) {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (passwordEncoder.matches(request.getOldPassword(), principal.getPassword())) {
            User user = userRepository.findById(principal.getId()).orElseThrow(
                    () -> new ResourceNotFoundException("User", "id", principal.getId())
            );

            user.setPassword(passwordEncoder.encode(request.getNewPassword()));

            updateSecurityContext(user);
        } else {
            throw new AppException("old password is wrong");
        }
    }

    @Override
    public List<UserProfile> getList() {
        return userRepository.findAll().stream().
                map(UserProfile::valueOf).
                collect(Collectors.toList());
    }

    private UserPrincipal updateSecurityContext(User user) {
        UserPrincipal newPrincipal = UserPrincipal.valueOf(user);

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(newPrincipal, null, newPrincipal.getAuthorities())
        );

        return newPrincipal;
    }
}
