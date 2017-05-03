package app.models.repositories;

import app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by aleksei on 03.05.17.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByNameAndPassword(String name, String password);

}
