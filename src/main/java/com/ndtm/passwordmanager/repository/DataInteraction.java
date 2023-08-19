package com.ndtm.passwordmanager.repository;

import com.ndtm.passwordmanager.userActions.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DataInteraction extends CrudRepository<User, Integer> {

    Optional<User> findByLogin(String login);
}
