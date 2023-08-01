package com.ndtm.passwordmanager.GUI;

import com.ndtm.passwordmanager.PasswordManagerApplication;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

/** TODO:
 * 1. Проверка на правильность данных в mouseReleased
 */
public abstract class StageManager extends Stage {
    public static Stage currentStage;

    public static void openAuthGui() throws IOException {
        Group group = new Group();
        Parent root;

        root = FXMLLoader.load(PasswordManagerApplication.class.getResource("authPasswordManager.fxml"));
        group.getChildren().add(root);
        AuthGui.setAuthGui(group);

        Scene scene = new Scene(group, 400, 600);
        scene.setFill(Color.TRANSPARENT);
        settingStage(scene);
    }


    public static void openPasswordManagerGui() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PasswordManagerApplication.class.getResource("passwordManagerMain.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        scene.setFill(Color.TRANSPARENT);
        settingStage(scene);
    }

    public static void settingStage(Scene scene) throws IOException{
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
        currentStage = stage;
    }

}
