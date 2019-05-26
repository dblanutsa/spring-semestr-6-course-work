package dmitriy.blanutsa.coursework.controller;

import dmitriy.blanutsa.coursework.controller.dto.UserProfile;
import dmitriy.blanutsa.coursework.security.UserPrincipal;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@PreAuthorize("isAuthenticated()")
public class UserController {

    @Transactional
    @GetMapping("/user/me")
    public UserProfile getCurrentUser(@AuthenticationPrincipal UserPrincipal currentUser) {
        return UserProfile.valueOf(currentUser);
    }
}
