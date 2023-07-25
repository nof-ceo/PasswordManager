package com.ndtm.passwordmanager;

import com.ndtm.passwordmanager.GUI.StageManager;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class PasswordManagerApplication extends Application {

//	public static void main(String[] args) {
//		SpringApplication.run(PasswordManagerApplication.class, args);
//	}

    @Override
    public void start(Stage stage) throws IOException {
        StageManager.currentStage = stage;
        StageManager.openAuthGui();
    }
}
