package com.ndtm.passwordmanager.controller;

import com.ndtm.passwordmanager.GUI.MainGui;
import com.ndtm.passwordmanager.userActions.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class PasswordManagerMainController {

    @FXML
    private Label userLogin;

    @FXML
    private Label userEmail;

    @FXML
    private Rectangle rectangleUnderShieldIcon;

    @FXML
    private Rectangle passwordsMenuButton;

    private double mousePressedX;
    private double mousePressedY;
    private MainGui mainGui;

    @FXML
    public void initialize() {
        userLogin.setText(UserService.currentActiveUser.getLogin());
        userEmail.setText(UserService.currentActiveUser.getEmail());
        mainGui = new MainGui();
    }

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

    @FXML
    public void enteredToPasswordButtonBorder() {
        rectangleUnderShieldIcon.setOpacity(1.0);
    }

    @FXML
    public void exitedFromPasswordButtonBorder() {
        rectangleUnderShieldIcon.setOpacity(0.0);
    }

    @FXML
    public void selectPasswordsMenu() {
        passwordsMenuButton.setOpacity(1.0);

        mainGui.showPasswordsMenu();
    }
}
