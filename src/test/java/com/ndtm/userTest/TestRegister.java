package com.ndtm.userTest;

import com.ndtm.passwordmanager.userActions.User;
import com.ndtm.passwordmanager.userRepository.UserInteraction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/** TODO:
 * 1.логин и пароль буду в хешированном виде
 */
@RunWith(MockitoJUnitRunner.class)
public class TestRegister {
    @Mock
    UserInteraction userInteraction;

    @Test
    public void register() {
        byte[] login = "loginTest".getBytes();
        byte[] password = "testPassword".getBytes();

        User user = new User("John", "Doe", login, password, "testEmail@gmail.com".getBytes());

        when(userInteraction.save(any(User.class))).thenReturn(user);
        assertEquals(user.getEmail(), userInteraction.save(user).getEmail());

        when(userInteraction.findById(0)).thenReturn(java.util.Optional.of(user));
        assertEquals(0, userInteraction.findById(0).get().getId());
    }
}
