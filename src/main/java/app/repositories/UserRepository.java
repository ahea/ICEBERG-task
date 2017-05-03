package app.repositories;

import app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by aleksei on 03.05.17.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByName(String name);

    User findByNameAndPassword(String name, String password);

}
