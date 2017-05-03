package app.exceptions;

/**
 * Created by aleksei on 03.05.17.
 */
public class UserNotFoundException extends Throwable {

    public UserNotFoundException(String message){
        super(message);
    }
}
