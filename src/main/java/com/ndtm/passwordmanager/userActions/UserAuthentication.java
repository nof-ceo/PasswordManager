package com.ndtm.passwordmanager.userActions;

import com.ndtm.passwordmanager.userRepository.UserInteraction;
import org.springframework.beans.factory.annotation.Autowired;

public class UserAuthentication{

    @Autowired
    UserInteraction userInteraction;
}
