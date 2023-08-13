package com.ndtm.passwordmanager.savedDataRepository;

import org.springframework.stereotype.Service;

@Service
public class SavedDataInteractionService {

    private SavedDataInteraction savedDataInteraction;

    public SavedDataInteractionService(SavedDataInteraction savedDataInteraction) {
        this.savedDataInteraction = savedDataInteraction;
    }
}
