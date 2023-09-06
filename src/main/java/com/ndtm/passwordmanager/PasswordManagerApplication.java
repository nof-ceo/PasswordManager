package com.ndtm.passwordmanager;

import com.ndtm.passwordmanager.GUI.StageManager;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

public class PasswordManagerApplication extends Application {

    private ConfigurableApplicationContext applicationContext;

	@Override
    public void start(Stage stage) throws IOException{
        applicationContext = new SpringApplicationBuilder(StockUiApp.class).run();

        applicationContext.publishEvent(new StageReadyEvent(stage));
    }

    private class StageReadyEvent extends StageManager{
        public StageReadyEvent(Stage stage) throws IOException {
            StageManager.currentStage = stage;
            openAuthGui();
        }
    }
}
