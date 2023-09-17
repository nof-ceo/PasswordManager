package com.ndtm.passwordmanager;

import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
public class StockUiApp {
    public static void main(String[] args) {
        Application.launch(PasswordManagerApplication.class, args);
    }
}
