package app.services;

import app.exceptions.TaskNotFoundException;
import app.exceptions.WrongStatusException;
import app.models.Task;
import app.models.User;

import java.util.List;

/**
 * Created by aleksei on 03.05.17.
 */
public interface TaskService {

    List getTasksWithStatus(User user, int status)
            throws WrongStatusException;

    Task saveTask(Task task);

    void deleteTaskById(User user, Integer id)
            throws TaskNotFoundException;

}
