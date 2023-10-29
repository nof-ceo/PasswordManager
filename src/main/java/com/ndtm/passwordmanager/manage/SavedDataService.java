package com.ndtm.passwordmanager.manage;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ndtm.passwordmanager.repository.SavedDataInteraction;
import com.ndtm.passwordmanager.userActions.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Optional;

/** TODO:
 * 1. пароль будет закодирован в AES, т.к хешированные данные нельзя вернуть в прежнюю форму
 */

@Service
public class SavedDataService {

    @Autowired
    SavedDataInteraction savedDataInteraction;

    private static SavedDataInteraction staticSavedDataInteraction;

    @PostConstruct
    public void init() {
        staticSavedDataInteraction = savedDataInteraction;
    }

    public SavedData saveData(String siteTitle, String siteUrl, String login,
                                String password, String creditCard, String expiredDate,
                                    String cvv, String phoneNumber) {
        String passwordHash = BCrypt.withDefaults().hashToString(12, password.toCharArray());

        SavedData savedData = new SavedData(siteTitle, siteUrl, login, passwordHash, creditCard, expiredDate, cvv, phoneNumber);
        savedData.setUserId(UserService.currentActiveUser.getId());

        return staticSavedDataInteraction.save(savedData);
    }

    public SavedData saveData(SavedData savedData) {
        savedData.setUserId(UserService.currentActiveUser.getId());

        return staticSavedDataInteraction.save(savedData);
    }

    public Optional<SavedData> deleteByDataId(int id) {
        Optional<SavedData> deletedData = staticSavedDataInteraction.findById(id);

        staticSavedDataInteraction.deleteById(id);

        return deletedData;
    }

    public Optional<SavedData> findByLogin(String login) {
        return staticSavedDataInteraction.findByLogin(login);
    }

    public Optional<SavedData> findById(int id) {
        return staticSavedDataInteraction.findById(id);
    }


    //AES-256
    public SavedData encryptData(SavedData savedData) {
        SecretKey secretKey = generateKey(savedData.getPassword());
        IvParameterSpec iv = generateInitializationVector();

        SavedData encryptedData = new SavedData();

        encryptedData.setUserId(savedData.getUserId());
        encryptedData.setKey(Base64.getEncoder().encodeToString(secretKey.getEncoded()));
        encryptedData.setInitVector(iv.getIV());
        encryptedData.setSiteTitle(encryptField(savedData.getSiteTitle(), secretKey, iv));
        encryptedData.setSiteUrl(encryptField(savedData.getSiteUrl(), secretKey, iv));
        encryptedData.setLogin(encryptField(savedData.getLogin(), secretKey, iv));
        encryptedData.setPassword(encryptField(savedData.getPassword(), secretKey, iv));

        return encryptedData;
    }

    public String encryptField(String textField, SecretKey key, IvParameterSpec initVector){
        if(textField.isBlank()) return "";

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, initVector);
            byte[] text = cipher.doFinal(textField.getBytes());
            return Base64.getEncoder().encodeToString(text);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "";
    }

    public String decryptField(String textField, SecretKey key, IvParameterSpec iv) {
        if(textField.isBlank()) return "";

        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, iv);
            byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(textField));
            return new String(plainText);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "";
    }

    public SecretKey generateKey(String title) {
        SecretKey key = null;

        try {
            SecureRandom secureRandom = new SecureRandom();
            byte[] salt = new byte[16];
            secureRandom.nextBytes(salt);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec keySpec = new PBEKeySpec(title.toCharArray(), salt, 65536, 256);
            key = new SecretKeySpec(factory.generateSecret(keySpec).getEncoded(), "AES");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return key;
    }

    public IvParameterSpec generateInitializationVector() {
        byte[] initializationVector = new byte[16];
        new SecureRandom().nextBytes(initializationVector);
        return new IvParameterSpec(initializationVector);
    }
}
