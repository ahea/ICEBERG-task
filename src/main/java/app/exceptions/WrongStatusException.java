package app.exceptions;

/**
 * Created by aleksei on 03.05.17.
 */
public class WrongStatusException extends Throwable {

    public WrongStatusException(String message){
        super(message);
    }
}
