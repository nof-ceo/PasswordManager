package com.ndtm.passwordmanager.savedDataRepository;

import com.ndtm.passwordmanager.manage.SavedData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SavedDataInteraction extends CrudRepository<SavedData, Integer> {

    Optional<SavedData> findBySiteTitle(String siteTitle);
    Optional<SavedData> deleteBySiteTitle(String siteTitle);
}
