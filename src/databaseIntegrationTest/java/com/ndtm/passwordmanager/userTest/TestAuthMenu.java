package com.ndtm.passwordmanager;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ndtm.passwordmanager.PasswordManagerApplication;
import com.ndtm.passwordmanager.userActions.User;
import com.ndtm.passwordmanager.userActions.UserService;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/** TODO:
 * тесты будут выполняться через методы users type classes
 */
@RunWith(SpringRunner.class)
@EntityScan("com.ndtm.passwordmanager.*")
@DataJpaTest
@EnableJpaRepositories(basePackages = "com.ndtm.passwordmanager.repository")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = "com.ndtm.passwordmanager.userActions")
public class TestAuthMenu {

    @Autowired
    UserService userRegister;

    String login = "testLogin";
    String passwordHash = BCrypt.withDefaults().hashToString(12, "testPassword".toCharArray());

    User user = new User("John", "Doe", login, passwordHash, "testEmail@gmail.com");

    @Test
    public void testRegister() {
        assertEquals(user, userRegister.makeRegister(user));
    }

    @Test
    public void testAuthentication() {
        userRegister.makeRegister(user);

        assertTrue(userRegister.makeAuth(login, "testPassword".toCharArray()));
    }

    @Test
    public void testCorrectFields() {
        assertTrue(userRegister.checkEmailFieldForCorrectAndNotNull("email@test.com"));
        assertTrue(userRegister.checkPasswordFieldForCorrectAndNotNull("1234"));
        assertTrue(userRegister.checkLoginFieldForCorrectAndNotNull("testLogin"));
    }

    private static Stream<Arguments> dataEmail() {
        return Stream.of(Arguments.of("email test@test.com"), Arguments.of(".email@test.com"),
                Arguments.of("email..@test.com"), Arguments.of("email@test..com"),
                    Arguments.of("email@test.c"), Arguments.of("email@gmail.com-" +
                        ""),
                        Arguments.of(""), Arguments.of("testEmail@gmail.com"));
    }

    private static Stream<Arguments> dataLogin() {
        return Stream.of(Arguments.of("log"), Arguments.of("testLogin"),
                Arguments.of(""), Arguments.of("log "));

    }

    private static Stream<Arguments> dataPassword() {
        return Stream.of(Arguments.of("pas"), Arguments.of("passwor d"),
                Arguments.of(""), Arguments.of("pass@"));

    }

    @ParameterizedTest
    @MethodSource("dataEmail")
    public void testNonCorrectEmailField(String email) {
        userRegister.makeRegister(user);
        assertFalse(userRegister.checkEmailFieldForCorrectAndNotNull(email));
    }

    @ParameterizedTest
    @MethodSource("dataLogin")
    public void testNonCorrectLoginField(String login) {
        userRegister.makeRegister(user);
        assertFalse(userRegister.checkLoginFieldForCorrectAndNotNull(login));
    }

    @ParameterizedTest
    @MethodSource("dataPassword")
    public void testNonCorrectPassword(String password) {
        assertFalse(userRegister.checkPasswordFieldForCorrectAndNotNull(password));
    }
}
