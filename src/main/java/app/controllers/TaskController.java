package app.controllers;


import app.exceptions.TaskNotFoundException;
import app.exceptions.UserNotFoundException;
import app.exceptions.WrongPasswordException;
import app.exceptions.WrongStatusException;
import app.models.Task;
import app.models.User;
import app.services.TaskService;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by aleksei on 03.05.17.
 */

@RestController
public class TaskController {

    private UserService userService;
    private TaskService taskService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @Autowired
    public void setTaskService(TaskService taskService){
        this.taskService = taskService;
    }

    @RequestMapping(value = "/tasks/name={name}&password={password}&status={status}",
            method = RequestMethod.GET)
    public List getTasks(@PathVariable String name,
                         @PathVariable String password,
                         @PathVariable Integer status)
            throws WrongPasswordException, UserNotFoundException, WrongStatusException{
        User user = userService.getUserByCredentials(name, password);
        List list = taskService.getTasksWithStatus(user, status);
        return list;
    }

    @RequestMapping(value = "/tasks/new/name={name}&password={password}",
            method = RequestMethod.POST)
    public Task saveTask(@PathVariable String name,
                         @PathVariable String password,
                         @RequestBody Task task)
    throws WrongPasswordException {
        User user;
        try{
            user = userService.getUserByCredentials(name, password);
        } catch (UserNotFoundException e) {
            user = userService.createUserWithCredentials(name, password);
        }
        task.setOwner(user);
        task = taskService.saveTask(task);
        task.setOwner(null);
        return task;
    }

    @RequestMapping(value = "/tasks/delete/name={name}&password={password}&task={id}",
            method = RequestMethod.POST)
    public void deleteTask(@PathVariable String name,
                           @PathVariable String password,
                           @PathVariable Integer id)
    throws UserNotFoundException, WrongPasswordException, TaskNotFoundException{
        User user = userService.getUserByCredentials(name, password);
        taskService.deleteTaskById(user, id);
    }

}
