package com.ndtm.passwordmanager.userActions;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ndtm.passwordmanager.repository.UserDataInteraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

/** TODO:
 * 1. проверять верефицирован ли email в makeRegister
 * 2. можно заменить библиотеку bcrypt, на bcrypt в spring security
 */

@Service
public class UserService {

    @Autowired
    UserDataInteraction userDataInteraction;

    private static final String EMAIL_PATTERN =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private static final String PASSWORD_AND_LOGIN_PATTERN = "^([a-z-A-Z-0-9_]{4,})$";

    static UserDataInteraction staticUserDataInteraction;

    @PostConstruct
    public void init() {
        UserService.staticUserDataInteraction = userDataInteraction;
    }

    public static User currentUser;

    public User makeRegister(String firstName, String lastName, String login, char[] password, String email) {
        String passwordHash = BCrypt.withDefaults().hashToString(12, password);
        User user = new User(firstName, lastName, login, passwordHash, email);
        return staticUserDataInteraction.save(user);
    }

    public User makeRegister(User user) {
        return staticUserDataInteraction.save(user);
    }

    public Optional<User> findByLogin(String login) {
        return staticUserDataInteraction.findByLogin(login);
    }

    public Optional<User> findByEmail(String email) {
        return staticUserDataInteraction.findByEmail(email);
    }

    public boolean makeAuth(String inputLogin, char[] inputPassword) {

        Optional<User> foundedUser = staticUserDataInteraction.findByLogin(inputLogin);

        if(foundedUser.isPresent()) {
            boolean result = BCrypt.verifyer().verify(inputPassword, foundedUser.get().getPassword()).verified;
            if(result)
                currentUser = foundedUser.get();
            return result;
        } else {
            return false;
        }
    }

    public boolean checkEmailFieldForCorrectAndNotNull(String email) {
        return !email.isBlank() && email.matches(EMAIL_PATTERN) && staticUserDataInteraction.findByEmail(email).isEmpty();
    }
    public boolean checkLoginFieldForCorrectAndNotNull(String login) {
        return !login.isBlank() && login.matches(PASSWORD_AND_LOGIN_PATTERN) && staticUserDataInteraction.findByLogin(login).isEmpty();
    }

    public boolean checkPasswordFieldForCorrectAndNotNull(String password) {
        return !password.isBlank() && password.matches(PASSWORD_AND_LOGIN_PATTERN);
    }
}
