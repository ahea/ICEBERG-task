package app.controllers;

import app.exceptions.TaskNotFoundException;
import app.exceptions.UserNotFoundException;
import app.exceptions.WrongPasswordException;
import app.exceptions.WrongStatusException;
import app.models.enums.ServerErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by aleksei on 03.05.17.
 */
@ControllerAdvice
public class ErrorHandlingController {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ServerErrors> handleUserNotFoundException(
            UserNotFoundException e){
        return new ResponseEntity<>(ServerErrors.USER_NOT_FOUND,
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<ServerErrors> handleWrongPasswordException(
            WrongPasswordException e){
        return new ResponseEntity<>(ServerErrors.WRONG_PASSWORD,
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongStatusException.class)
    public ResponseEntity<ServerErrors> handleWrongStatusException(
            WrongStatusException e){
        return new ResponseEntity<>(ServerErrors.WRONG_STATUS,
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ServerErrors> handleTaskNotFoundException(
            TaskNotFoundException e){
        return new ResponseEntity<>(ServerErrors.TASK_NOT_FOUND,
                HttpStatus.BAD_REQUEST);
    }

}