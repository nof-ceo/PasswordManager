package com.ndtm.passwordmanager.repository;

import com.ndtm.passwordmanager.manage.SavedData;
import com.ndtm.passwordmanager.userActions.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface DataInteraction extends JpaRepository {

    // заменить когда будет extends JpaRepo
    Optional<User> findByLogin(byte[] login);
    Optional<SavedData> deleteByTitle(String title);
    Optional<SavedData> findByTitle(String title);
}
