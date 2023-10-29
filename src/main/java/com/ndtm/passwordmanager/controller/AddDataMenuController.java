package com.ndtm.passwordmanager.controller;

import com.ndtm.passwordmanager.GUI.AddDataGui;
import com.ndtm.passwordmanager.GUI.MainGui;
import com.ndtm.passwordmanager.element.DataBlock;
import com.ndtm.passwordmanager.manage.SavedData;
import com.ndtm.passwordmanager.manage.SavedDataService;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class AddDataMenuController {

    @FXML
    private TextField titleField;

    @FXML
    private TextField urlField;

    @FXML
    private TextField loginField;

    @FXML
    private TextField passwordField;

    @FXML
    private Text titleFieldWarning;

    @FXML
    private Text textTitleCannotBeEmpty;

    @FXML
    public Button addButton;

    private static TextField editingTitleField;
    private static TextField editingUrlField;
    private static TextField editingLoginField;
    private static TextField editingPasswordField;
    public static Button editingButton;
    private static Text editingTitleFieldWarning;
    private static Text editingTextTittleCannotBeEmpty;

    private double mousePressedX;
    private double mousePressedY;

    private static AddDataGui addDataGui;
    private static MainGui mainGui;
    private final SavedDataService savedDataService = new SavedDataService();

    @FXML
    public void initialize() {
        mainGui = new MainGui();
        addDataGui = new AddDataGui();

        editingTitleField = titleField;
        editingUrlField = urlField;
        editingLoginField = loginField;
        editingPasswordField = passwordField;
        editingButton = addButton;
        editingTitleFieldWarning = titleFieldWarning;
        editingTextTittleCannotBeEmpty = textTitleCannotBeEmpty;
    }

    @FXML
    public void dragWindow(MouseEvent event) {
        double x = (event.getScreenX() - mousePressedX);
        double y = (event.getScreenY() - mousePressedY);

        AddDataGui.setPointOfWindow(x, y);
    }

    @FXML
    public void mousePoint(MouseEvent event) {
        mousePressedX = event.getX();
        mousePressedY = event.getY();
    }

    @FXML
    public void addingDataBlock() {
        if(!titleField.getText().isBlank()) {
            SavedData savedData = new SavedData(titleField.getText(), urlField.getText(), loginField.getText(),
                    passwordField.getText(), null, null, null, null);

            SavedData encryptedData = savedDataService.encryptData(savedData) ;
            savedDataService.saveData(encryptedData);

            savedData.setId(encryptedData.getId());
            savedData.setKey(encryptedData.getKey());
            savedData.setInitVector(encryptedData.getInitVector());

            addDataGui.closeAddDataGui();
            new MainGui().showDataBlock(savedData);
        } else {
            titleFieldWarning.setVisible(true);
            textTitleCannotBeEmpty.setVisible(true);
        }
    }

    public void fillFields(DataBlock dataBlock) {
        SavedData editingData = dataBlock.getAddingData();
        addDataGui.showEditDataGui();

        EventHandler<MouseEvent> mouseClicked = event -> {
            if(!editingTitleField.getText().isBlank()) {
                addDataGui.closeAddDataGui();
                SavedData newData = new SavedData(editingTitleField.getText(), editingUrlField.getText(), editingLoginField.getText(),
                        editingPasswordField.getText(), null, null, null, null);
                newData.setId(editingData.getId());

                SavedData encryptedEditingData = savedDataService.encryptData(newData);
                encryptedEditingData.setId(editingData.getId());
                
                mainGui.changeDataOfDataBlock(newData);
                dataBlock.setAddingData(newData);

                savedDataService.saveData(encryptedEditingData);
            } else {
                editingTitleFieldWarning.setVisible(true);
                editingTextTittleCannotBeEmpty.setVisible(true);
            }
        };
        AddDataMenuController.editingButton.setOnMouseClicked(mouseClicked);
        AddDataMenuController.editingButton.setText("Edit");

        editingTitleField.setText(editingData.getSiteTitle());
        editingUrlField.setText(editingData.getSiteUrl());
        editingLoginField.setText(editingData.getLogin());
        editingPasswordField.setText(editingData.getPassword());
    }
}
