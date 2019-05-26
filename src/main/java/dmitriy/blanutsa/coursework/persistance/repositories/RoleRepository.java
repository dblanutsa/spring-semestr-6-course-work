package dmitriy.blanutsa.coursework.persistance.repositories;

import dmitriy.blanutsa.coursework.persistance.entities.Role;
import dmitriy.blanutsa.coursework.security.RoleConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(RoleConstants roleName);
}
