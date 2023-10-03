package com.ndtm.passwordmanager.controller;

import com.ndtm.passwordmanager.GUI.*;
import com.ndtm.passwordmanager.userActions.User;
import com.ndtm.passwordmanager.userActions.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;


import static com.ndtm.passwordmanager.GUI.AuthGui.*;


public class AuthMenuController {

    private UserService userService = new UserService();

    @FXML
    public Label buttonForChooseLogin;
    @FXML
    public Label buttonForChooseRegister;
    @FXML
    public Rectangle uselessDesignElement;

    private static Label staticButtonForChooseLogin;
    private static Label staticButtonForChooseRegister;
    private static Rectangle staticUselessDesignElement;

    static Rectangle useless;

    private double mousePressedX;
    private double mousePressedY;

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
        staticButtonForChooseLogin.setStyle("-fx-text-fill: #c1b3ff");
        staticButtonForChooseRegister.setStyle("-fx-text-fill: #c3c3c3");
        staticUselessDesignElement.setLayoutX(68);
        staticUselessDesignElement.setWidth(75);

        AuthGui.hideRegisterMenu();
        AuthGui.showLoginMenu();
    }

    @FXML
    public void registerChosen() {
        buttonForChooseLogin.setStyle("-fx-text-fill: #c3c3c3");
        buttonForChooseRegister.setStyle("-fx-text-fill: #c1b3ff");
        uselessDesignElement.setLayoutX(230);
        uselessDesignElement.setWidth(120);

        useless = uselessDesignElement;

        AuthGui.hideLoginMenu();
        AuthGui.showRegisterMenu();
    }

    @FXML
    public void initialize() {
        staticButtonForChooseLogin = buttonForChooseLogin;
        staticButtonForChooseRegister = buttonForChooseRegister;
        staticUselessDesignElement = uselessDesignElement;
    }

    public void typedInEmailField(String email) {
        if(userService.checkEmailFieldForCorrectAndNotNull(email))
            showCorrectIcon(emailRequirements);
        else
            showWrongIcon(emailRequirements ,email.isBlank());
    }

    public void typedInLoginField(String login) {
        if(userService.checkLoginFieldForCorrectAndNotNull(login))
            showCorrectIcon(loginRequirements);
        else
            showWrongIcon(loginRequirements, login.isBlank());
    }

    public void typeInPasswordField(String password) {
        if(userService.checkPasswordFieldForCorrectAndNotNull(password))
            showCorrectIcon(passwordRequirements);
        else
            showWrongIcon(passwordRequirements, password.isBlank());
    }

    public void registerButtonClicked(TextField firstNameField, TextField surNameField,
                                        TextField loginField, PasswordField passwordField, TextField emailField) {

        StageManager.executor.execute(() -> {
            User user = new User(firstNameField.getText(), surNameField.getText(), loginField.getText(), passwordField.getText(), emailField.getText());

            try {
                userService.sendVerificationLink(user);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                clearRequirementIcons();
                firstNameField.clear();
                surNameField.clear();
                emailField.clear();
                loginField.clear();
                passwordField.clear();
            }
        });

        continueRegisterNotification(emailField.getText());

        loginChosen();
    }

    public boolean loginButtonClicked(String login, String password) {
        return userService.makeAuth(login, password.toCharArray());
    }

}
