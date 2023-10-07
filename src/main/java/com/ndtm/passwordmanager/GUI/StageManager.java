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
    AuthGui authGui;

    public static Stage currentStage;

    public static Group group;

    private static Scene scene;

    public static ExecutorService executor = Executors.newFixedThreadPool(5);

    public void openAuthGui() throws IOException {
        group = new Group();
        Parent root;

        root = FXMLLoader.load(PasswordManagerApplication.class.getResource("authPasswordManager.fxml"));
        group.getChildren().add(root);
        authGui = new AuthGui(group);

        scene = new Scene(group, 400, 600);
        scene.setFill(Color.TRANSPARENT);
        settingStage(scene);
    }


    public static void openPasswordManagerGui() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(PasswordManagerApplication.class.getResource("passwordManagerMain.fxml"));
        group.getChildren().add(fxmlLoader.load());

        scene.setRoot(group);
        scene.setFill(Color.TRANSPARENT);

        currentStage.setWidth(1000);
        currentStage.setHeight(600);
        currentStage.setX(currentStage.getX()/2);
        currentStage.show();
    }

    private static void settingStage(Scene scene) throws IOException{
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        currentStage = stage;
    }

}
