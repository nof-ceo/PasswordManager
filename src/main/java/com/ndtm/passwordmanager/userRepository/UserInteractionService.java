package com.ndtm.passwordmanager.userRepository;

import org.springframework.stereotype.Service;

@Service
public class UserInteractionService {

    private UserInteraction userInteraction;

    public UserInteractionService(UserInteraction userInteraction) {
        this.userInteraction = userInteraction;
    }

}
