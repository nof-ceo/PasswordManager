package com.ndtm.passwordmanager.passwordManageTest;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ndtm.passwordmanager.manage.*;
import com.ndtm.passwordmanager.userActions.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;


/** TODO:
 * 1. изменить ссылки и title сайтов на их encode
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan(basePackages = { "com.ndtm.passwordmanager.*" })
public class TestManagePassword {

    @Autowired
    SavedDataService savedDataService;

    @Autowired
    UserService userService;

    String login = "testLogin";

    User user = new User("Dree", "Doe", "testLoginUser", "testPasswordUser", "testEmail@gmail.com");

    SavedData savedData;

    @Test
    public void testSavePassword() {
        userService.makeRegister(user);
        userService.makeAuth("testLoginUser", "testPasswordUser".toCharArray());

        savedData = new SavedData("vk.com", "https://vk.com", login, BCrypt.withDefaults().hashToString(12, "testPassword".toCharArray()), null, null, null, null);

        SavedData encryptedData = savedDataService.encryptData(savedData);

        assertEquals(encryptedData, savedDataService.saveData(encryptedData));
    }

    @Test
    public void testFoundData() {
        userService.makeRegister(user);
        userService.makeAuth("testLoginUser", "testPasswordUser".toCharArray());

        savedData = new SavedData("vk.com", "https://vk.com", login, BCrypt.withDefaults().hashToString(12, "testPassword".toCharArray()), null, null, null, null);

        SavedData encryptedData = savedDataService.encryptData(savedData);

        savedDataService.saveData(encryptedData);
        SavedData foundedData = savedDataService.saveData(encryptedData);

        assertEquals(encryptedData, savedDataService.findByLogin(encryptedData.getLogin()).get());
        assertEquals(encryptedData, savedDataService.findById(encryptedData.getId()).get());
    }



}
