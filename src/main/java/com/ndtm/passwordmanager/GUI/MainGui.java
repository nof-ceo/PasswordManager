package com.ndtm.passwordmanager.GUI;

import com.ndtm.passwordmanager.controller.PasswordManagerMainController;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class MainGui extends PasswordManagerMainController {

    private static Group group = StageManager.group;

    public boolean passwordMenuShown = false;

    public static void setPointOfWindow(double x, double y) {
        StageManager.currentStage.setX(x);
        StageManager.currentStage.setY(y);
    }

    public void showPasswordsMenu() {
        if(!passwordMenuShown)
            setAddDataButton();
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

        group.getChildren().add(addButton);
        passwordMenuShown = true;
        System.out.println(1);
    }

}
