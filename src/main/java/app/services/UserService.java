package app.services;

import app.exceptions.UserNotFoundException;
import app.exceptions.WrongPasswordException;
import app.models.User;

/**
 * Created by aleksei on 03.05.17.
 */
public interface UserService {

    User getUserByCredentials(String name, String password)
            throws WrongPasswordException, UserNotFoundException;

    User createUserWithCredentials(String name, String password);

}
