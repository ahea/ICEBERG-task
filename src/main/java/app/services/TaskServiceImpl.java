package app.services;

import app.exceptions.TaskNotFoundException;
import app.exceptions.WrongStatusException;
import app.models.Task;
import app.models.User;
import app.models.enums.TaskStatus;
import app.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by aleksei on 03.05.17.
 */
@Service
public class TaskServiceImpl implements TaskService{

    private TaskRepository taskRepository;

    @Autowired
    public void setTaskRepository(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @Override
    public List getTasksWithStatus(User user, int status) throws
        WrongStatusException{

        if (status < 0 || TaskStatus.values().length <= status)
            throw new WrongStatusException("Wrong status value specified");

        TaskStatus taskStatus = TaskStatus.values()[status];
        LinkedList<Task> result = new LinkedList<>();
        for (Task task : user.getTasks()){
            if (task.getStatus() == taskStatus) {
                task.setOwner(null);
                result.add(task);
            }
        }
        return result;
    }

    @Override
    public Task saveTask(Task task){
        return taskRepository.save(task);
    }

    @Override
    public void deleteTaskById(User user, Integer id) throws TaskNotFoundException{
        Task task = taskRepository.findOne(id);
        if (task == null) throw new TaskNotFoundException("Task with such id was not found");
        if (!task.getOwner().equals(user)) throw new TaskNotFoundException("Permission for task deletion denied");
        taskRepository.delete(task);
    }

}
