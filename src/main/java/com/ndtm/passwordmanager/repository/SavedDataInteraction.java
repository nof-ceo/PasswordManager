package com.ndtm.passwordmanager.repository;

import com.ndtm.passwordmanager.manage.SavedData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SavedDataInteraction extends CrudRepository<SavedData, Integer> {

    Optional<SavedData> findByLogin(String login);
}
