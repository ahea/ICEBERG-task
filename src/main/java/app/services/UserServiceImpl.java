package app.services;

import app.exceptions.UserNotFoundException;
import app.exceptions.WrongPasswordException;
import app.models.User;
import app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by aleksei on 03.05.17.
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByCredentials(String name, String password)
            throws WrongPasswordException, UserNotFoundException{
        User user = userRepository.findByName(name);
        if (user == null)
            throw new UserNotFoundException("User with such credentials was not found");
        if (!user.getPassword().equals(password))
            throw new WrongPasswordException("Wrong password");
        return user;
    }

    @Override
    public User createUserWithCredentials(String name, String password){
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        return userRepository.save(user);
    }

}
