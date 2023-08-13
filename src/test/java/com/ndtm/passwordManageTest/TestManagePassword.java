package com.ndtm.passwordManageTest;

import com.ndtm.passwordmanager.manage.SavedData;
import com.ndtm.passwordmanager.savedDataRepository.SavedDataInteraction;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.junit.runner.RunWith;


/** TODO:
 * 1. изменить ссылки и title сайтов на их хеш
 */

@RunWith(MockitoJUnitRunner.class)
public class TestManagePassword {

    @Mock
    SavedDataInteraction savedDataInteraction;

    @Test
    public void savePassword() {
        byte[] login = "testLogin".getBytes();
        byte[] password = "testPassword".getBytes();

        SavedData savedData = new SavedData("vk.com", "https://vk.com", login, password, null, null, null, null);

        when(savedDataInteraction.save(any(SavedData.class))).thenReturn(savedData);
        assertEquals(savedData, savedDataInteraction.save(savedData));

        when(savedDataInteraction.findBySiteTitle("vk.com")).thenReturn(Optional.of(savedData));
        assertEquals(Optional.of(savedData), savedDataInteraction.findBySiteTitle("vk.com"));
    }

    @Test
    public void deletePassword() {
        byte[] login = "testLogin".getBytes();
        byte[] password = "testPassword".getBytes();

        SavedData savedData = new SavedData("vk.com", "https://vk.com", login, password, null, null, null, null);

        when(savedDataInteraction.deleteBySiteTitle("vk.com")).thenReturn(Optional.of(savedData));
        assertEquals(Optional.of(savedData), savedDataInteraction.deleteBySiteTitle("vk.com"));

        when(savedDataInteraction.findBySiteTitle("vk.com")).thenReturn(Optional.empty());
        assertTrue(savedDataInteraction.findBySiteTitle("vk.com").equals(Optional.empty()));
    }

}
