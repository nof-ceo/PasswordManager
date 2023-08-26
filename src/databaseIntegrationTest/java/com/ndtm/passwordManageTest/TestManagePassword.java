package com.ndtm.passwordManageTest;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ndtm.passwordmanager.PasswordManagerApplication;
import com.ndtm.passwordmanager.manage.SavedData;
import com.ndtm.passwordmanager.manage.SavedDataService;
import com.ndtm.passwordmanager.repository.SavedDataInteraction;
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


/** TODO:
 * 1. изменить ссылки и title сайтов на их хеш
 */

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = { "com.ndtm.passwordmanager.*" })
@EntityScan("com.ndtm.passwordmanager.*")
@ContextConfiguration(classes = {PasswordManagerApplication.class})
@DataJpaTest
@EnableJpaRepositories(basePackages = "com.ndtm.passwordmanager.repository")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestManagePassword {

    @Autowired
    SavedDataService savedDataService;

    @Autowired
    UserService userService;

    String login = "testLogin";
    char[] password = "testPassword".toCharArray();

    User user = new User("Dree", "Doe", "testLoginUser", BCrypt.withDefaults().hashToString(12, "testPasswordUser".toCharArray()), "testEmail@gmail.com".getBytes());

    @Test
    public void testSavePassword() {

        String passwordHash = BCrypt.withDefaults().hashToString(12, password);

        SavedData savedData = new SavedData("vk.com", "https://vk.com", login, passwordHash, null, null, null, null);
        userService.makeRegister(user);
        userService.makeAuth("testLoginUser", "testPasswordUser".toCharArray());

        assertEquals(savedData, savedDataService.saveData(savedData));
    }

    @Test
    public void testFoundData() {

        String passwordHash = BCrypt.withDefaults().hashToString(12, password);

        SavedData savedData = new SavedData("vk.com", "https://vk.com", login, passwordHash, null, null, null, null);

        userService.makeRegister(user);
        userService.makeAuth("testLoginUser", "testPasswordUser".toCharArray());
        savedDataService.saveData(savedData);

        assertEquals(savedData, savedDataService.findByLogin(login).get());
    }



}
