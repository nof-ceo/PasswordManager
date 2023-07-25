package com.ndtm.passwordmanager.controller;

import com.ndtm.passwordmanager.GUI.MainGui;
import com.ndtm.passwordmanager.HelloApplication;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class PasswordManagerMainController {
    private double mousePressedX;
    private double mousePressedY;

    @FXML
    public void dragWindow(MouseEvent event) {
        double x = (event.getScreenX() - mousePressedX);
        double y = (event.getScreenY() - mousePressedY);

        MainGui.setPointOfWindow(x, y);
    }

    @FXML
    public void mousePoint(MouseEvent event) {
        mousePressedX = event.getX();
        mousePressedY = event.getY();
    }
}
