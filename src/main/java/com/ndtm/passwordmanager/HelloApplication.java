package com.ndtm.passwordmanager;

import com.ndtm.passwordmanager.GUI.AuthGui;
import com.ndtm.passwordmanager.GUI.StageManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

// заменить этот класс на PasswordManagerApplication
public class HelloApplication extends Application {
    static Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException {
        StageManager.currentStage = stage;
        StageManager.openAuthGui();
    }

    public static void windowPointSet(double x, double y) {
        mainStage.setX(x);
        mainStage.setY(y);
    }

}