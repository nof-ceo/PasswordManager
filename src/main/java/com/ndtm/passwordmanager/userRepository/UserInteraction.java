package com.ndtm.passwordmanager.repository;

import com.ndtm.passwordmanager.Data;
import com.ndtm.passwordmanager.userActions.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataInteraction extends CrudRepository<Object, Integer>{

}
