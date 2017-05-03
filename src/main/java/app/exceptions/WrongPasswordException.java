package app.exceptions;

/**
 * Created by aleksei on 03.05.17.
 */
public class WrongPasswordException extends Throwable{

    public WrongPasswordException(String message){
        super(message);
    }

}
