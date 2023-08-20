package com.ndtm.userTest;

import com.ndtm.passwordmanager.PasswordManagerApplication;
import com.ndtm.passwordmanager.userActions.User;
import com.ndtm.passwordmanager.repository.UserDataInteraction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import java.security.MessageDigest;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;

/** TODO:
 * 1.логин и пароль буду в хешированном виде
 * 2. сменить md5 на AES-256
 */
@RunWith(SpringRunner.class)
@ComponentScan(basePackages = { "com.ndtm.passwordmanager.*" })
@EntityScan("com.ndtm.passwordmanager.*")
@ContextConfiguration(classes = {PasswordManagerApplication.class})
@SpringBootTest
@EnableJpaRepositories(basePackages = "com.ndtm.passwordmanager.repository")
@EnableAutoConfiguration
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestAuthMenu {
    @Autowired
    UserDataInteraction userInteraction;

    @Test
    public void testRegister() {
        byte[] login = "loginTest".getBytes();
        byte[] password = "testPassword".getBytes();

        String loginHash = hashingProcess(login, password);

        User user = new User("John", "Doe", loginHash, password, "testEmail@gmail.com".getBytes());

        assertEquals(user, userInteraction.save(user));
        userInteraction.delete(user);
    }

    @Test
    public void testAuthentication() {
        byte[] login = "loginTest".getBytes();
        byte[] password = "testPassword".getBytes();

        String loginHash = hashingProcess(login, password);


        User user = new User("Dree", "Doe", loginHash, password, "testEmail@gmail.com".getBytes());

        userInteraction.save(user);
        User foundedUser = userInteraction.findByLogin(loginHash).get();

        assertEquals(user.getLogin(), foundedUser.getLogin());
        userInteraction.delete(user);
    }

    public String hashingProcess(byte[] login, byte[] password) {
        try {
            StringBuilder hash = new StringBuilder();

            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(("22EE" + Arrays.toString(md5.digest((Arrays.toString(md5.digest(login)) + ""
                    + Arrays.toString(md5.digest(password))).getBytes()))).getBytes());

            for (byte a : bytes) hash.append(String.format("%02X", a));

            return hash.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
