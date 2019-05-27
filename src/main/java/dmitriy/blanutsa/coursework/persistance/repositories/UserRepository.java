package dmitriy.blanutsa.coursework.persistance.repositories;

import dmitriy.blanutsa.coursework.persistance.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    Optional<User> findByLoginOrEmail(String username, String email);

    List<User> findByIdIn(List<Long> userIds);

    User findByLogin(String username);

    Boolean existsByLogin(String username);

    Boolean existsByEmail(String email);
}
