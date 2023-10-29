package com.ndtm.passwordmanager.GUI;

import com.ndtm.passwordmanager.controller.PasswordManagerMainController;
import com.ndtm.passwordmanager.element.DataBlock;
import com.ndtm.passwordmanager.manage.SavedData;
import com.ndtm.passwordmanager.manage.SavedDataService;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class MainGui {

    public static Group group = StageManager.group;

    private static Pane editingDataBlock;

    private final SavedDataService savedDataService = new SavedDataService();

    private static boolean passwordMenuShown = false;

    private static int dataBlockY = 0;

    private PasswordManagerMainController managerMainController = new PasswordManagerMainController();

    private static boolean rightClickOnDataBlock = false;

    public boolean isRightClickOnDataBlock() {
        return rightClickOnDataBlock;
    }

    public void setRightClickOnDataBlock(boolean rightClickOnDataBlock) {
        MainGui.rightClickOnDataBlock = rightClickOnDataBlock;
    }

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

        addButton.setX(580);
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

    public void showDataBlock(SavedData savedData) {
        DataBlock dataBlock = new DataBlock(savedData, dataBlockY);

        Pane pane = dataBlock.getPane();
        MenuItem edit = dataBlock.getEdit();
        MenuItem delete = dataBlock.getDelete();

        EventHandler<ActionEvent> deleteEvent = event -> {
            pane.toBack();
            int index = Integer.parseInt(pane.getId());
            group.getChildren().remove(pane);
            savedDataService.deleteByDataId(dataBlock.getAddingData().getId());

            upAllDataBlocks(index);

            dataBlockY -= 90;
        };
        EventHandler<ActionEvent> editEvent = event -> {
            editingDataBlock = pane;
            managerMainController.editData(dataBlock);
        };

        delete.setOnAction(deleteEvent);
        edit.setOnAction(editEvent);

        dataBlockY += 90;

        group.getChildren().add(pane);
    }

    public void upAllDataBlocks(int index) {
        for(int i = index; i < group.getChildren().size(); i++) {
            Pane pane = (Pane) group.getChildren().get(i);
            if(pane.getLayoutY() > 40) {
                pane.setLayoutY(pane.getLayoutY() - 90);
                pane.setId(Integer.parseInt(pane.getId()) - 1 + "");
            }
        }
    }

    public void changeDataOfDataBlock(SavedData newData) {
        Label title = (Label) editingDataBlock.getChildren().get(1);
        ImageView favicon = (ImageView) editingDataBlock.getChildren().get(2);
        Label savedLogin = (Label) editingDataBlock.getChildren().get(4);
        Label savedPassword = (Label) editingDataBlock.getChildren().get(6);

        title.setText(newData.getSiteTitle());
        favicon.setImage(managerMainController.parseFavicon(newData.getSiteUrl()));
        savedLogin.setText(newData.getLogin());
        savedPassword.setText("• ".repeat(newData.getPassword().length()));
    }

}
