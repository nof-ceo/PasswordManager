package com.ndtm.passwordmanager.GUI;

import com.ndtm.passwordmanager.PasswordManagerApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public abstract class StageManager extends Stage {
    public static Stage currentStage;

    public static void openAuthGui() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PasswordManagerApplication.class.getResource("authPasswordManager.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);
        scene.setFill(Color.TRANSPARENT);
        settingStage(scene);
    }


    public static void openPasswordManagerGui() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PasswordManagerApplication.class.getResource("passwordManagerMain.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        scene.setFill(Color.TRANSPARENT);
        settingStage(scene);
    }

    private static void settingStage(Scene scene) throws IOException{
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
        currentStage = stage;
    }

}
