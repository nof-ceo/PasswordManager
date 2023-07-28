package com.ndtm.userTest;

import com.ndtm.passwordmanager.userActions.User;
import com.ndtm.passwordmanager.repository.DataInteraction;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.when;

/** TODO:
 * 1.логин и пароль буду в хешированном виде
 */
@RunWith(MockitoJUnitRunner.class)
public class TestRegister {

    @Mock
    DataInteraction dataInteraction;

    @Test
    public void register() {
        byte[] login = "loginTest".getBytes();
        byte[] password = "testPassword".getBytes();

        User user = new User("John", "Doe", login, password);

        when(dataInteraction.save(any(User.class))).thenReturn(user);
        when(dataInteraction.findByLogin(login)).thenReturn(Optional.of(user));
    }
}
