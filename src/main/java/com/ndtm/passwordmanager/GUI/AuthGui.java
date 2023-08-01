package com.ndtm.passwordmanager.GUI;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** TODO:
 *  1. Сделать ForkJoinPool для tuneX
 *  2. Привязать Action к кнопке
 *  3. Email sender
 */
public class AuthGui extends StageManager {
    static Group group;
    private static final List listLoginElements = new ArrayList();
    private static final List listRegisterElements = new ArrayList();

    public static void setPointOfWindow(double x, double y) {
        AuthGui.currentStage.setX(x);
        AuthGui.currentStage.setY(y);
    }

    public static void closeAuthWindow() {
        AuthGui.currentStage.hide();
    }

    public static void setAuthGui(Group groupFromStageManager) {
        group = groupFromStageManager;
        tuneLoginMenu();
        tuneRegisterMenu();
    }

    private static void tuneLoginMenu() {
        TextField loginField = new TextField();
        loginField.setLayoutX(100);
        loginField.setLayoutY(175);
        loginField.setPrefColumnCount(16);
        loginField.setStyle("-fx-background-radius: 20 20 20 20;" +
                            "-fx-background-color: #c3c3c3;");
        loginField.setPromptText("Login");

        PasswordField passwordField = new PasswordField();
        passwordField.setLayoutX(100);
        passwordField.setLayoutY(230);
        passwordField.setPrefColumnCount(16);
        passwordField.setStyle("-fx-background-radius: 20 20 20 20;" +
                               "-fx-background-color: #c3c3c3;");
        passwordField.setPromptText("Password");

        Button loginButton = new Button();
        loginButton.setMinHeight(46);
        loginButton.setMinWidth(116);
        loginButton.setLayoutX(150);
        loginButton.setLayoutY(400);
        loginButton.setStyle("-fx-background-radius: 30 30 30 30;" +
                             "-fx-border-radius: 30 30 30 30;" +
                             "-fx-background-color: transparent;" +
                             "-fx-border-color: #c1b3ff;" +
                             "-fx-font-size: 21;" +
                             "-fx-text-fill: #c1b3ff;");
        loginButton.setText("Login");
        EventHandler<MouseEvent> mouseReleased = event -> {
            try {
                AuthGui.closeAuthWindow();
                openPasswordManagerGui();
            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setHeaderText("Open ERROR");
                alert.setContentText("Cannot open a passwordManagerMain.fxml");
                alert.showAndWait();

                ex.printStackTrace();
                currentStage.show();
            }
        };
        loginButton.setOnMouseReleased(mouseReleased);

        group.getChildren().add(loginField);
        group.getChildren().add(passwordField);
        group.getChildren().add(loginButton);

        listLoginElements.add(loginField);
        listLoginElements.add(passwordField);
        listLoginElements.add(loginButton);
    }
    private static void tuneRegisterMenu() {
        TextField firstNameField = new TextField();
        firstNameField.setPrefWidth(100);
        firstNameField.setLayoutX(87);
        firstNameField.setLayoutY(176);
        firstNameField.setStyle("-fx-background-color: #c3c3c3;" +
                                "-fx-background-radius: 20 20 20 20;");
        firstNameField.setPromptText("First name");

        TextField lastNameField = new TextField();
        lastNameField.setPrefWidth(100);
        lastNameField.setLayoutX(212);
        lastNameField.setLayoutY(176);
        lastNameField.setStyle("-fx-background-color: #c3c3c3;" +
                               "-fx-background-radius: 20 20 20 20;");
        lastNameField.setPromptText("Last name");

        TextField emailField = new TextField();
        emailField.setMinWidth(226);
        emailField.setLayoutX(87);
        emailField.setLayoutY(230);
        emailField.setStyle("-fx-background-color: #c3c3c3;" +
                "-fx-background-radius: 20 20 20 20;");
        emailField.setPromptText("Email");

        TextField loginField = new TextField();
        loginField.setMinWidth(226);
        loginField.setLayoutX(87);
        loginField.setLayoutY(285);
        loginField.setStyle("-fx-background-color: #c3c3c3;" +
                "-fx-background-radius: 20 20 20 20;");
        loginField.setPromptText("Login");

        PasswordField passwordField = new PasswordField();
        passwordField.setMinWidth(226);
        passwordField.setLayoutX(87);
        passwordField.setLayoutY(340);
        passwordField.setStyle("-fx-background-color: #c3c3c3;" +
                "-fx-background-radius: 20 20 20 20;");
        passwordField.setPromptText("Password");

        Button registerButton = new Button("Register");
        registerButton.minHeight(46);
        registerButton.minWidth(116);
        registerButton.setLayoutX(145);
        registerButton.setLayoutY(450);
        registerButton.setStyle("-fx-background-radius: 30 30 30 30;" +
                                "-fx-border-radius: 30 30 30 30;" +
                                "-fx-background-color: transparent;" +
                                "-fx-border-color: #c1b3ff;" +
                                "-fx-font-size: 21;" +
                                "-fx-text-fill: #c1b3ff;");

        listRegisterElements.add(firstNameField);
        listRegisterElements.add(lastNameField);
        listRegisterElements.add(emailField);
        listRegisterElements.add(loginField);
        listRegisterElements.add(passwordField);
        listRegisterElements.add(registerButton);
    }


    public static void hideLoginMenu() {
        group.getChildren().remove(1, 4);
    }

    public static void showLoginMenu() {
        group.getChildren().addAll(listLoginElements);
    }

    public static void hideRegisterMenu() {
        group.getChildren().remove(1, 7);
    }

    public static void showRegisterMenu() {
        group.getChildren().addAll(listRegisterElements);
    }
}