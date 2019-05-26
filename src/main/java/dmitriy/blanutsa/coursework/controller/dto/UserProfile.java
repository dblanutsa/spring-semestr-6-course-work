package dmitriy.blanutsa.coursework.controller.dto;

import dmitriy.blanutsa.coursework.security.UserPrincipal;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
public class UserProfile {

    private String login;
    private String firstName;
    private String lastName;
    private List<GrantedAuthority> roles;

    public static UserProfile valueOf(UserPrincipal principal) {
        UserProfile profile = new UserProfile();
        profile.setLogin(principal.getLogin());
        profile.setFirstName(principal.getFirstName());
        profile.setLastName(principal.getLastName());
        profile.setRoles(new ArrayList<>(principal.getAuthorities()));

        return profile;
    }
}
