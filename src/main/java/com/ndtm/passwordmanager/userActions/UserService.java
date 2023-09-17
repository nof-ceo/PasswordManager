package com.ndtm.passwordmanager.userActions;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ndtm.passwordmanager.repository.UserDataInteraction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.internet.MimeMessage;
import java.util.Optional;
import java.util.UUID;

/** TODO:
 */

@Service
public class UserService {

    @Autowired
    private UserDataInteraction userDataInteraction;

    @Autowired
    private JavaMailSender javaMailSender;

    private static final String EMAIL_PATTERN =
            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private static final String PASSWORD_AND_LOGIN_PATTERN = "^([a-z-A-Z-0-9_]{4,})$";

    static UserDataInteraction staticUserDataInteraction;

    // мб есть вариант без static
    static JavaMailSender staticJavaMailSender;

    @PostConstruct
    public void init() {
        UserService.staticUserDataInteraction = userDataInteraction;
        UserService.staticJavaMailSender = javaMailSender;
    }

    public static User currentActiveUser;
    public static User currentVerificationUser;

    public User makeRegister(String firstName, String lastName, String login, char[] password, String email) {
        String passwordHash = BCrypt.withDefaults().hashToString(12, password);
        User user = new User(firstName, lastName, login, passwordHash, email);
        return staticUserDataInteraction.save(user);
    }

    public User makeRegister(User user) {
        String passwordHash = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
        user.setPassword(passwordHash);

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
                currentActiveUser = foundedUser.get();
            return result;
        } else {
            return false;
        }
    }

    public void sendVerificationLink(User user) throws Exception{
        currentVerificationUser = user;

        String verificationToken = UUID.randomUUID().toString();

        currentVerificationUser.setVerificationToken(verificationToken);

        // in future change to real site
        String verifyLink = "localhost:8080/verify?verificationToken=" + verificationToken;

        MimeMessage mimeMessage = staticJavaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        helper.setTo(user.getEmail());
        helper.setSubject("Verification");
        helper.setText("<br>" +
                "Please click the link below to verify your registration:<br>" +
                "<h3><a href=" + verifyLink +  " target=\"_self\">VERIFY</a></h3>", true);

        staticJavaMailSender.send(mimeMessage);
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
