package app.controllers;


import app.exceptions.UserNotFoundException;
import app.exceptions.WrongPasswordException;
import app.exceptions.WrongStatusException;
import app.models.Task;
import app.models.User;
import app.services.TaskService;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by aleksei on 03.05.17.
 */

@Controller
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

    @RequestMapping(value = "/tasks?name={name}&password={password}&status={status}",
            method = RequestMethod.GET)
    public List getTasks(@RequestParam String name,
                         @RequestParam String password,
                         @RequestParam Integer status)
            throws WrongPasswordException, UserNotFoundException, WrongStatusException{
        User user = userService.getUserByCredentials(name, password);
        List list = taskService.getTasksWithStatus(user, status);
        return list;
    }

    @RequestMapping(value = "/tasks/new?name={name}&password={password}",
            method = RequestMethod.POST)
    public Task saveTask(@RequestParam String name,
                         @RequestParam String password,
                         @RequestBody Task task)
    throws WrongPasswordException {
        User user;
        try{
            user = userService.getUserByCredentials(name, password);
        } catch (UserNotFoundException e) {
            user = userService.createUserWithCredentials(name, password);
        }
        task.setOwner(user);
        return taskService.saveTask(task);
    }

}
