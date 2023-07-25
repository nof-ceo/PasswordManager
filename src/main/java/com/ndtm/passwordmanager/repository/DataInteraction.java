package com.ndtm.passwordmanager.repository;

import com.ndtm.passwordmanager.manage.WebSite;
import com.ndtm.passwordmanager.userActions.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DataInteraction extends JpaRepository {

    // заменить когда будет extends JpaRepo
    Optional<User> findByLogin(byte[] login);
    Optional<WebSite> deleteByTitle(String title);
    Optional<WebSite> findByTitle(String title);
}
