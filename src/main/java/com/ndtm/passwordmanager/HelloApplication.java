package com.ndtm.passwordmanager;

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
        mainStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("passwordManagerMain.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        scene.setFill(Color.TRANSPARENT);
        mainStage.initStyle(StageStyle.TRANSPARENT);
        mainStage.setScene(scene);
        mainStage.show();
    }

    public static void windowPointSet(double x, double y) {
        mainStage.setX(x);
        mainStage.setY(y);
    }

}