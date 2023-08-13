package com.ndtm.passwordmanager.userRepository;

import com.ndtm.passwordmanager.userActions.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInteraction extends CrudRepository<User, Integer>{

    Optional<User> findByLogin(byte[] login);
}
