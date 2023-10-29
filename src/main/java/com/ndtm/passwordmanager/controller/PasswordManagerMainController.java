package com.ndtm.passwordmanager.controller;

import com.google.common.net.InternetDomainName;
import com.ndtm.passwordmanager.GUI.MainGui;
import com.ndtm.passwordmanager.element.DataBlock;
import com.ndtm.passwordmanager.userActions.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.net.URI;

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
    private static MainGui mainGui;
    private static AddDataMenuController addDataMenuController;

    @FXML
    public void initialize() {
        userLogin.setText(UserService.currentActiveUser.getLogin());
        userEmail.setText(UserService.currentActiveUser.getEmail());

        mainGui = new MainGui();
        addDataMenuController = new AddDataMenuController();
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

    public void editData(DataBlock dataBlock) {
        addDataMenuController.fillFields(dataBlock);
    }

    public Image parseFavicon(String url){
        Image favicon = new Image("file:src/main/resources/com/ndtm/passwordmanager/icons8-вопрос-60.png");
        try {
            URI uri = new URI(url);
            InternetDomainName domainName = InternetDomainName.from(uri.getHost()).topPrivateDomain();
            String domain = domainName.toString();

            favicon = new Image("https://www.google.com/s2/favicons?domain=" + domain + "&sz=128");
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {

            return favicon;
        }
    }


}
