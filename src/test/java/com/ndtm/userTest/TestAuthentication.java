package com.ndtm.userTest;

import com.ndtm.passwordmanager.userActions.User;
import com.ndtm.passwordmanager.repository.DataInteraction;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/** TODO:
 * 1.логин и пароль буду в хешированном виде
 */
@RunWith(MockitoJUnitRunner.class)
public class TestAuthentication {

    @Mock
    DataInteraction dataInteraction;

    @Test
    public void authentication() {
        byte[] login = "loginTest".getBytes();
        byte[] password = "testPassword".getBytes();

        User user = new User("John", "Doe", login, password, "testEmail@gmail.com".getBytes());

        User foundedUser = dataInteraction.findByLogin(user.getLogin()).get();
        assertEquals(foundedUser.getLogin(), user.getLogin());

        User wrongFoundedUser = dataInteraction.findByLogin("sdfds".getBytes()).get();
        assertNull(wrongFoundedUser.getLogin());
    }
}
