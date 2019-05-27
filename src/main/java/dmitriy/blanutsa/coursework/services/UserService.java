package dmitriy.blanutsa.coursework.services;

import dmitriy.blanutsa.coursework.controller.dto.ResetPasswordRequest;
import dmitriy.blanutsa.coursework.controller.dto.UserProfile;
import dmitriy.blanutsa.coursework.security.UserPrincipal;

import java.util.List;

public interface UserService {

    UserProfile getProfile(UserPrincipal principal);

    UserProfile updateProfile(UserProfile profile);

    void resetPassword(ResetPasswordRequest request);

    List<UserProfile> getList();
}
