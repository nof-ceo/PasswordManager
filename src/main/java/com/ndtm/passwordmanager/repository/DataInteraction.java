package com.ndtm.passwordmanager.repository;

import com.ndtm.passwordmanager.manage.WebSite;
import com.ndtm.passwordmanager.userActions.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DataInteraction
//        extends JpaRepository
{

    // заменить когда будет extends JpaRepo
    int save(User user);

    Optional<User> findByLogin(byte[] login);
    Optional<WebSite> deleteByTitle(String title);
    Optional<WebSite> findByTitle(String title);
}
