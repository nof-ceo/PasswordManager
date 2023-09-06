package com.ndtm.passwordmanager.GUI;

import com.ndtm.passwordmanager.PasswordManagerApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** TODO:
 */
public abstract class StageManager extends Stage {

    AuthGui authGui = new AuthGui();

    public static Stage currentStage;

    public static ExecutorService executor = Executors.newFixedThreadPool(5);

    public void openAuthGui() throws IOException {
        Group group = new Group();
        Parent root;

        root = FXMLLoader.load(PasswordManagerApplication.class.getResource("authPasswordManager.fxml"));
        group.getChildren().add(root);
        authGui.setAuthGui(group);

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

    private static void settingStage(Scene scene) throws IOException{
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
        currentStage = stage;
    }

}
