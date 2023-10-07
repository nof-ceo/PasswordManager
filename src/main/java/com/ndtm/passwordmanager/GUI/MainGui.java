package com.ndtm.passwordmanager.GUI;

import com.ndtm.passwordmanager.controller.PasswordManagerMainController;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;


public class MainGui extends PasswordManagerMainController {

    public static Group group = StageManager.group;

    private boolean passwordMenuShown = false;

    public static void setPointOfWindow(double x, double y) {
        StageManager.currentStage.setX(x);
        StageManager.currentStage.setY(y);
    }

    public void showPasswordsMenu() {
        if(!passwordMenuShown) {
            setAddDataButton();
        }
    }

    // выполнять, если не выбран passwords
    private void setAddDataButton() {
        ImageView addButton = new ImageView();
        Image image = new Image("file:src/main/resources/com/ndtm/passwordmanager/icons8-плюс-2-96.png");

        addButton.setImage(image);

        addButton.setX(565);
        addButton.setY(12);
        addButton.setFitWidth(30);
        addButton.setFitHeight(30);

        EventHandler<MouseEvent> mouseClicked = event -> {
            try {
                AddDataGui addDataGui = new AddDataGui();
                addDataGui.showAddDataGui();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };
        addButton.setOnMouseClicked(mouseClicked);

        group.getChildren().add(addButton);
        passwordMenuShown = true;
    }

}
