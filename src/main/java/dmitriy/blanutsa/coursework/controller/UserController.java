package dmitriy.blanutsa.coursework.controller;

import dmitriy.blanutsa.coursework.controller.dto.ResetPasswordRequest;
import dmitriy.blanutsa.coursework.controller.dto.UserProfile;
import dmitriy.blanutsa.coursework.security.UserPrincipal;
import dmitriy.blanutsa.coursework.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@PreAuthorize("isAuthenticated()")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    @GetMapping("/profile")
    public UserProfile getProfile(@AuthenticationPrincipal UserPrincipal principal) {
        return userService.getProfile(principal);
    }

    @Transactional
    @PostMapping("/profile")
    public UserProfile updateProfile(@Valid @RequestBody UserProfile profile) {
        return userService.updateProfile(profile);
    }

    @PostMapping("/resetPassword")
    public void resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
        userService.resetPassword(request);
    }

    @GetMapping("/list")
    public List<UserProfile> getList() {
        return userService.getList();
    }
}
