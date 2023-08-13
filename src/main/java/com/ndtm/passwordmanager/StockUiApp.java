package com.ndtm.passwordmanager;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StockUiApp {
    public static void main(String[] args) {
        Application.launch(PasswordManagerApplication.class, args);
    }
}
