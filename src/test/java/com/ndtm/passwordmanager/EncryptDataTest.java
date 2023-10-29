package com.ndtm.passwordmanager;

import com.ndtm.passwordmanager.manage.SavedDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@DataJpaTest
@ComponentScan(basePackages = { "com.ndtm.passwordmanager.*" })
public class EncryptDataTest {

    @Autowired
    SavedDataService savedDataService;

    @Test
    public void testEncryptKey() {
        SecretKey secretKey = savedDataService.generateKey("vk");
        IvParameterSpec iv = savedDataService.generateInitializationVector();

        String encryptedTitle = savedDataService.encryptField("vk", secretKey, iv);
        SecretKeySpec keyForKey = new SecretKeySpec(encryptedTitle.getBytes(), "AES");

        String encryptedKey = savedDataService.encryptField(Base64.getEncoder().encodeToString(secretKey.getEncoded()), keyForKey, iv);
        String decryptedKey = savedDataService.decryptField(encryptedKey, keyForKey, iv);

        assertEquals(Base64.getEncoder().encodeToString(secretKey.getEncoded()), decryptedKey);
    }

    @Test
    public void testEncryptData() {
        SecretKey secretKey = savedDataService.generateKey("github");
        IvParameterSpec iv = savedDataService.generateInitializationVector();

        String data = "github";

        String encryptedData = savedDataService.encryptField(data, secretKey, iv);
        String decryptedData = savedDataService.decryptField(encryptedData, secretKey, iv);

        assertEquals(data, decryptedData);
    }
}
