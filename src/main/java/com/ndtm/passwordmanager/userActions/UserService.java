package com.ndtm.passwordmanager.userActions;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ndtm.passwordmanager.repository.UserDataInteraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/** TODO:
 * 1. проверять верефицирован ли email в makeRegister
 * 2. можно заменить библиотеку bcrypt, на bcrypt в spring security
 */

@Service
public class UserService {

    @Autowired
    UserDataInteraction userDataInteraction;

    public static User currentUser;

    public User makeRegister(String firstName, String lastName, String login, char[] password, byte[] email) {
        String passwordHash = BCrypt.withDefaults().hashToString(12, password);
        User user = new User(firstName, lastName, login, passwordHash, email);
        return userDataInteraction.save(user);
    }

    public User makeRegister(User user) {
        return userDataInteraction.save(user);
    }

    public Optional<User> findByLogin(String login) {
        return userDataInteraction.findByLogin(login);
    }

    public boolean makeAuth(String inputLogin, char[] inputPassword) {

        Optional<User> foundedUser = userDataInteraction.findByLogin(inputLogin);

        if(foundedUser.isPresent()) {
            boolean result = BCrypt.verifyer().verify(inputPassword, foundedUser.get().getPassword()).verified;
            if(result)
                currentUser = foundedUser.get();
            return result;
        } else {
            return false;
        }
    }

}
