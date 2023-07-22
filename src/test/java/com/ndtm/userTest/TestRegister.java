package com.ndtm.userTest;

import com.ndtm.passwordmanager.userActions.User;
import com.ndtm.passwordmanager.repository.DataInteraction;
import org.junit.jupiter.api.Test;

public class TestRegister {
    DataInteraction dataInteraction;

    @Test
    public void register() {
        byte[] login = "loginTest".getBytes();
        // вместо готового пароля, будем вызывать метод хеширования
        byte[] password = "testPassword".getBytes();

        User user = new User("John", "Doe", login, password);

        //when(dataInteraction.save(any(userActions.User.class))).thenReturn(user);
        // сменить findById на findBy?
        //when(dataInteraction.findById(1)).thenReturn(Optional.of(user));
    }
}
