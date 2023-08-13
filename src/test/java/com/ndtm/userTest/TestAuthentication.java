package com.ndtm.userTest;

import com.ndtm.passwordmanager.userActions.User;
import com.ndtm.passwordmanager.userRepository.UserInteraction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


/** TODO:
 * 1.логин и пароль буду в хешированном виде
 */
@RunWith(MockitoJUnitRunner.class)
public class TestAuthentication {

    @Mock
    UserInteraction userInteraction;

    @Test
    public void authentication() {
        byte[] login = "loginTest".getBytes();
        byte[] password = "testPassword".getBytes();

        User user = new User("John", "Doe", login, password, "testEmail@gmail.com".getBytes());

        when(userInteraction.findByLogin(login)).thenReturn(Optional.of(user));
        assertEquals(Optional.of(user), userInteraction.findByLogin(login));
    }
}
