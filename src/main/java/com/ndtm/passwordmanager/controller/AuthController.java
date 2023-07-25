package com.ndtm.passwordmanager.controller;

import com.ndtm.passwordmanager.GUI.AuthGui;
import com.ndtm.passwordmanager.GUI.StageManager;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
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
