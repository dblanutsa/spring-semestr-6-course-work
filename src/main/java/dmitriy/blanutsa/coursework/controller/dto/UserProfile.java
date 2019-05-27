package dmitriy.blanutsa.coursework.controller.dto;

import dmitriy.blanutsa.coursework.persistance.entities.Role;
import dmitriy.blanutsa.coursework.persistance.entities.User;
import dmitriy.blanutsa.coursework.security.UserPrincipal;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
public class UserProfile {

    private Integer id;
    private String login;
    private String firstName;
    private String lastName;

    @NotBlank
    @Email
    private String email;
    private List<String> roles;

    public static UserProfile valueOf(UserPrincipal principal) {
        UserProfile profile = new UserProfile();
        profile.setLogin(principal.getLogin());
        profile.setFirstName(principal.getFirstName());
        profile.setLastName(principal.getLastName());
        profile.setEmail(principal.getEmail());
        profile.setRoles(principal.getAuthorities().stream().
                map(GrantedAuthority::getAuthority).
                collect(Collectors.toList()));

        return profile;
    }

    public static UserProfile valueOf(User user) {
        UserProfile profile = new UserProfile();
        profile.setId(user.getId());
        profile.setLogin(user.getLogin());
        profile.setFirstName(user.getFirstName());
        profile.setLastName(user.getLastName());
        profile.setEmail(user.getEmail());
        profile.setRoles(user.getRoles().stream().
                map((role) -> role.getName().name()).
                collect(Collectors.toList()));

        return profile;
    }
}
