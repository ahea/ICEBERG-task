package app.repositories;

import app.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by aleksei on 03.05.17.
 */
public interface TaskRepository extends JpaRepository<Task, Integer>{
}
