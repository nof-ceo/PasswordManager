package com.ndtm.passwordmanager.GUI;

import com.ndtm.passwordmanager.controller.AuthMenuController;
import com.ndtm.passwordmanager.userActions.UserService;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


import java.io.IOException;
import java.util.*;
import java.util.List;

/** TODO:
 *  3. Мб есть вариант исправить исчезновение register menu при повторном нажатии на register без переменной currentMenuIsLogin
 *  4. javafx через Task
 *  5. Индексация бд для оптимизации запросов
 */

public class AuthGui extends AuthMenuController {

    private final UserService userService = new UserService();

    private static Group group;

    private static final List listLoginElements = new ArrayList();
    private static final List listRegisterElements = new ArrayList();

    public static final ImageView loginRequirements = new ImageView();
    public static final ImageView emailRequirements = new ImageView();
    public static final ImageView passwordRequirements = new ImageView();

    private static boolean currentMenuIsLogin = true;

    public AuthGui(Group group) {
        this.group = group;
        tuneLoginMenu();
        tuneRegisterMenu();
    }

    public static void setPointOfWindow(double x, double y) {
        StageManager.currentStage.setX(x);
        StageManager.currentStage.setY(y);
    }

    public void closeAuthWindow() {
        StageManager.currentStage.close();
    }

    private void tuneLoginMenu() {
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

        Label errorLabel = new Label();
        errorLabel.setLayoutX(130);
        errorLabel.setLayoutY(340);
        errorLabel.setText("Incorrect login or password");
        errorLabel.setOpacity(0.0);
        errorLabel.setStyle("-fx-text-fill: #ff2d00;");

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
                if(loginButtonClicked(loginField.getText(), passwordField.getText())) {
                    group.getChildren().clear();
                    closeAuthWindow();
                    StageManager.openPasswordManagerGui();
                } else {
                    errorLabel.setOpacity(100.0);
                }
            } catch (IOException ex) {

            }
        };
        loginButton.setOnMouseReleased(mouseReleased);

        group.getChildren().add(loginField);
        group.getChildren().add(passwordField);
        group.getChildren().add(loginButton);
        group.getChildren().add(errorLabel);

        listLoginElements.add(loginField);
        listLoginElements.add(passwordField);
        listLoginElements.add(loginButton);
        listLoginElements.add(errorLabel);
    }

    private void tuneRegisterMenu() {
        TextField firstNameField = new TextField();
        firstNameField.setPrefWidth(100);
        firstNameField.setLayoutX(87);
        firstNameField.setLayoutY(176);
        firstNameField.setStyle("-fx-background-color: #c3c3c3;" +
                                "-fx-background-radius: 20 20 20 20;");
        firstNameField.setPromptText("First name");

        TextField surNameField = new TextField();
        surNameField.setPrefWidth(100);
        surNameField.setLayoutX(212);
        surNameField.setLayoutY(176);
        surNameField.setStyle("-fx-background-color: #c3c3c3;" +
                               "-fx-background-radius: 20 20 20 20;");
        surNameField.setPromptText("Surname");

        TextField emailField = new TextField();
        emailField.setMinWidth(226);
        emailField.setLayoutX(87);
        emailField.setLayoutY(230);
        emailField.setStyle("-fx-background-color: #c3c3c3;" +
                "-fx-background-radius: 20 20 20 20;");
        emailField.setPromptText("Email");
        setRequirementsPos(emailRequirements, 233);
        EventHandler<KeyEvent> keyTypedEmailField = event -> {
            typedInEmailField(emailField.getText());
        };

        emailField.setOnKeyTyped(keyTypedEmailField);

        TextField loginField = new TextField();
        loginField.setMinWidth(226);
        loginField.setLayoutX(87);
        loginField.setLayoutY(285);
        loginField.setStyle("-fx-background-color: #c3c3c3;" +
                "-fx-background-radius: 20 20 20 20;");
        loginField.setPromptText("Login");
        setRequirementsPos(loginRequirements, 288);
        EventHandler<KeyEvent> keyTypedLoginField = event -> {
            typedInLoginField(loginField.getText());
        };
        loginField.setOnKeyTyped(keyTypedLoginField);

        PasswordField passwordField = new PasswordField();
        passwordField.setMinWidth(226);
        passwordField.setLayoutX(87);
        passwordField.setLayoutY(340);
        passwordField.setStyle("-fx-background-color: #c3c3c3;" +
                "-fx-background-radius: 20 20 20 20;");
        passwordField.setPromptText("Password");
        setRequirementsPos(passwordRequirements, 343);
        EventHandler<KeyEvent> keyTypedPasswordField = event -> {
                typeInPasswordField(passwordField.getText());
        };
        passwordField.setOnKeyTyped(keyTypedPasswordField);


        Button registerButton = new Button("Register");
        registerButton.minHeight(46);
        registerButton.minWidth(116);
        registerButton.setLayoutX(150);
        registerButton.setLayoutY(450);
        registerButton.setStyle("-fx-background-radius: 30 30 30 30;" +
                                "-fx-border-radius: 30 30 30 30;" +
                                "-fx-background-color: transparent;" +
                                "-fx-border-color: #c1b3ff;" +
                                "-fx-font-size: 21;" +
                                "-fx-text-fill: #c1b3ff;");
        EventHandler<MouseEvent> registerButtonClicked = event -> {
            if(userService.checkPasswordFieldForCorrectAndNotNull(passwordField.getText()) &&
                    userService.checkEmailFieldForCorrectAndNotNull(emailField.getText()) &&
                        userService.checkLoginFieldForCorrectAndNotNull(loginField.getText()) && !firstNameField.getText().isBlank() &&
                            !surNameField.getText().isBlank()) {

                registerButtonClicked(firstNameField, surNameField, loginField, passwordField, emailField);

            } else {
                invalidRegisterAttemptNotification();
            }
        };
        registerButton.setOnMouseClicked(registerButtonClicked);

        listRegisterElements.add(firstNameField);
        listRegisterElements.add(surNameField);
        listRegisterElements.add(emailField);
        listRegisterElements.add(loginField);
        listRegisterElements.add(passwordField);
        listRegisterElements.add(registerButton);
        listRegisterElements.add(emailRequirements);
        listRegisterElements.add(loginRequirements);
        listRegisterElements.add(passwordRequirements);

    }

    public static void showCorrectIcon(ImageView requirements) {
        if(requirements.getImage() == null || !requirements.getImage().getUrl().equals("src/main/resources/com/ndtm/" +
                "passwordmanager/icons8-correct-104.png")) {
            group.getChildren().remove(requirements);

            Image image = new Image("file:src/main/resources/com/ndtm/passwordmanager/icons8-correct-104.png");
            requirements.setImage(image);

            group.getChildren().add(requirements);
        }
    }

    public static void showWrongIcon(ImageView requirements, boolean inputTextIsNull) {
        if(inputTextIsNull)
            group.getChildren().remove(requirements);
        else {
            group.getChildren().remove(requirements);

            Image image = new Image("file:src/main/resources/com/ndtm/passwordmanager/icons8-wrong-104.png");
            requirements.setImage(image);

            group.getChildren().add(requirements);
        }
    }

    public void setRequirementsPos(ImageView requirement, int y) {
        requirement.setX(315);
        requirement.setY(y);
        requirement.setFitHeight(20);
        requirement.setFitWidth(20);
    }

    public static void continueRegisterNotification(String email) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Continue");
        alert.setHeaderText("Continuation of registration");
        alert.setContentText("A verification message was sent to \"" + email +"\"");

        alert.show();
    }

    public void invalidRegisterAttemptNotification() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid");
        alert.setHeaderText("Register Error");
        alert.setContentText("Error: Fields must be non-null and meet requirements");

        alert.showAndWait();
    }

    public static void clearRequirementIcons() {
        emailRequirements.imageProperty().set(null);
        loginRequirements.imageProperty().set(null);
        passwordRequirements.imageProperty().set(null);
    }

    public static void hideLoginMenu() {
        if(currentMenuIsLogin) {
            group.getChildren().remove(1, group.getChildren().size());
        }
    }

    public static void showLoginMenu() {
        if(!currentMenuIsLogin) {
            group.getChildren().addAll(listLoginElements);
            currentMenuIsLogin = true;
        }
    }

    public static void hideRegisterMenu() {
        if (!currentMenuIsLogin) {
            group.getChildren().remove(1, group.getChildren().size());
        }
    }

    public static void showRegisterMenu() {
        if(currentMenuIsLogin) {
            group.getChildren().addAll(listRegisterElements);
            currentMenuIsLogin = false;
        }
    }
}