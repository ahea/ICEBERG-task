package app.exceptions;

/**
 * Created by aleksei on 03.05.17.
 */
public class TaskNotFoundException extends Throwable {

    public TaskNotFoundException(String message){
        super(message);
    }
}
