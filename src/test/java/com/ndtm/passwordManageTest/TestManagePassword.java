package com.ndtm.passwordManageTest;

import com.ndtm.passwordmanager.manage.WebSite;
import com.ndtm.passwordmanager.repository.DataInteraction;
import org.junit.jupiter.api.Test;
import java.util.Optional;
import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.when;

/** TODO:
 * 1. Изменить имя переменной webSite
 * 2. изменить ссылки и title сайтов на их хеш
 */
public class TestManagePassword {
    DataInteraction dataInteraction;
    @Test
    public void savePassword() {
        byte[] password = "testPassword".getBytes();

        WebSite webSite = new WebSite("vk.com", "https://vk.com", password);

        when(dataInteraction.save(any(WebSite.class))).thenReturn(webSite);
        when(dataInteraction.findByTitle("vk.com")).thenReturn(Optional.of(webSite));
    }

    @Test
    public void deletePassword() {
        byte[] password = "testPassword".getBytes();

        WebSite webSite = new WebSite("vk.com", "https://vk.com", password);

        when(dataInteraction.deleteByTitle("vk.com")).thenReturn(Optional.of(webSite));
        when(dataInteraction.findByTitle("vk.com")).thenReturn(Optional.empty());
    }

}
