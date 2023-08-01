package com.ndtm.passwordmanager.controller;

import com.ndtm.passwordmanager.GUI.AuthGui;
import com.ndtm.passwordmanager.GUI.StageManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class AuthController {

    public Label buttonForChoseLogin;
    public Label buttonForChoseRegister;
    public Rectangle uselessDesignElement;
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

    @FXML
    public void loginChosen() {
        buttonForChoseLogin.setStyle("-fx-text-fill: #c1b3ff");
        buttonForChoseRegister.setStyle("-fx-text-fill: #c3c3c3");
        uselessDesignElement.setLayoutX(68);
        uselessDesignElement.setWidth(75);

        AuthGui.hideRegisterMenu();
        AuthGui.showLoginMenu();
    }

    @FXML
    public void registerChosen() {
        buttonForChoseLogin.setStyle("-fx-text-fill: #c3c3c3");
        buttonForChoseRegister.setStyle("-fx-text-fill: #c1b3ff");
        uselessDesignElement.setLayoutX(230);
        uselessDesignElement.setWidth(120);

        AuthGui.hideLoginMenu();
        AuthGui.showRegisterMenu();
    }

}
