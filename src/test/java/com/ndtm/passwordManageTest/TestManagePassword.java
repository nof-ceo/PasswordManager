package com.ndtm.passwordManageTest;

import com.ndtm.passwordmanager.manage.WebSite;
import com.ndtm.passwordmanager.repository.DataInteraction;
import org.junit.jupiter.api.Test;

public class TestManagePassword {
    DataInteraction dataInteraction;

    @Test
    public void savePassword() {
        byte[] password = "testPassword".getBytes();

        // изменить имя переменной
        WebSite webSite = new WebSite("vk.com", "https://vk.com", password);

        //when(dataInteraction.save(any(WebSite.class))).thenReturn(webSite);
        // сменить findById на findBy?
        //when(dataInteraction.findById(1)).thenReturn(Optional.of(webSite));
    }

    @Test
    public void deletePassword() {
        byte[] password = "testPassword".getBytes();

        // изменить имя переменной
        WebSite webSite = new WebSite("vk.com", "https://vk.com", password);

        //when(dataInteraction.deleteBySite(any(WebSite.class))).thenReturn(webSite);
        // сменить findById на findBy?, использовать тип, который возвращает пустой Optional при методе .get()
        //when(dataInteraction.findByTitle("vk.com")).thenReturn(Optional.empty());
    }

}
