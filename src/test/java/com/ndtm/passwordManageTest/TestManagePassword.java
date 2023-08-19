package com.ndtm.passwordManageTest;

import com.ndtm.passwordmanager.PasswordManagerApplication;
import com.ndtm.passwordmanager.manage.SavedData;
import com.ndtm.passwordmanager.repository.SavedDataInteraction;
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

import static org.junit.jupiter.api.Assertions.assertEquals;


/** TODO:
 * 1. изменить ссылки и title сайтов на их хеш
 */

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = { "com.ndtm.passwordmanager.*" })
@EntityScan("com.ndtm.passwordmanager.*")
@ContextConfiguration(classes = {PasswordManagerApplication.class})
@SpringBootTest
@EnableJpaRepositories(basePackages = "com.ndtm.passwordmanager.userRepository")
@EnableAutoConfiguration
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TestManagePassword {

    @Autowired
    SavedDataInteraction savedDataInteraction;

    String login = "testLogin";
    byte[] password = "testPassword".getBytes();

    @Test
    public void testSavePassword() {

        SavedData savedData = new SavedData("vk.com", "https://vk.com", login, password, null, null, null, null);

        SavedData savedDataToDatabase = savedDataInteraction.save(savedData);
        assertEquals(savedData, savedDataToDatabase);

        savedDataInteraction.delete(savedData);
    }

    @Test
    public void testFoundData() {
        SavedData savedData = new SavedData("vk.com", "https://vk.com", login, password, null, null, null, null);

        savedDataInteraction.save(savedData);

        assertEquals(savedData.getLogin(), savedDataInteraction.findByLogin(login).get().getLogin());
        savedDataInteraction.delete(savedData);
    }



}
