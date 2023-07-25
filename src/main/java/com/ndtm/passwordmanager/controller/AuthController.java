package com.ndtm.passwordmanager.controller;

import com.ndtm.passwordmanager.GUI.AuthGui;
import com.ndtm.passwordmanager.GUI.StageManager;
import com.ndtm.passwordmanager.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AuthController {

    private double mousePressedX;
    private double mousePressedY;

    @FXML
    public void login(MouseEvent event) throws IOException {
        AuthGui.closeAuthWindow();
        StageManager.openPasswordManagerGui();
    }

    @FXML
    public void dragWindow(MouseEvent event) {
        double x = (event.getScreenX() - mousePressedX);
        double y = (event.getScreenY() - mousePressedY);

        AuthGui.setPointOfWindow(x, y);
    }

    @FXML
    public void mousePoint(MouseEvent event) {
        mousePressedX = event.getX();
        mousePressedY = event.getY();
    }
}
