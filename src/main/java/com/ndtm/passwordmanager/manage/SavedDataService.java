package com.ndtm.passwordmanager.manage;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.ndtm.passwordmanager.repository.SavedDataInteraction;
import com.ndtm.passwordmanager.userActions.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/** TODO:
 * 1. пароль будет закодирован в AES, т.к хешированные данные нельзя вернуть в прежнюю форму
 */

@Service
public class SavedDataService {

    @Autowired
    SavedDataInteraction savedDataInteraction;

    public SavedData saveData(String siteTitle, String siteUrl, String login, String password, byte[] creditCard, byte[] expiredDate, byte[] cvv, byte[] phoneNumber) {
        String passwordHash = BCrypt.withDefaults().hashToString(12, password.toCharArray());

        SavedData savedData = new SavedData(siteTitle, siteUrl, login, passwordHash, creditCard, expiredDate, cvv, phoneNumber);
        savedData.setUserId(UserService.currentActiveUser.getId());

        return savedDataInteraction.save(savedData);
    }

    public SavedData saveData(SavedData savedData) {
        savedData.setUserId(UserService.currentActiveUser.getId());

        return savedDataInteraction.save(savedData);
    }

    public Optional<SavedData> findByLogin(String login) {
        return savedDataInteraction.findByLogin(login);
    }
}
