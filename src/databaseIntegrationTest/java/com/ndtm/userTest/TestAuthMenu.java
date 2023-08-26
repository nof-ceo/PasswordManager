package com.ndtm.userTest;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ndtm.passwordmanager.PasswordManagerApplication;
import com.ndtm.passwordmanager.userActions.User;
import com.ndtm.passwordmanager.userActions.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** TODO:
 * тесты будут выполняться через методы users type classes
 */
@RunWith(SpringRunner.class)
@EntityScan("com.ndtm.passwordmanager.*")
@ContextConfiguration(classes = {PasswordManagerApplication.class})
@DataJpaTest
@EnableJpaRepositories(basePackages = "com.ndtm.passwordmanager.repository")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = "com.ndtm.passwordmanager.userActions")
public class TestAuthMenu {

    @Autowired
    UserService userRegister;

    String login = "testLogin";
    char[] password = "testPassword".toCharArray();

    @Test
    public void testRegister() {

        String passwordHash = BCrypt.withDefaults().hashToString(12, password);

        User user = new User("John", "Doe", login, passwordHash, "testEmail@gmail.com".getBytes());

        assertEquals(user, userRegister.makeRegister(user));
    }

    @Test
    public void testAuthentication() {

        String passwordHash = BCrypt.withDefaults().hashToString(12, password);

        User user = new User("Dree", "Doe", login, passwordHash, "testEmail@gmail.com".getBytes());
        userRegister.makeRegister(user);

        assertTrue(userRegister.makeAuth(login, password));
    }

}
