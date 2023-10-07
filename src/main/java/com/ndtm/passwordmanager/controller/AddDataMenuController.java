package com.ndtm.passwordmanager.controller;

import com.ndtm.passwordmanager.GUI.AddDataGui;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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

    private double mousePressedX;
    private double mousePressedY;

    AddDataGui addDataGui;

    @FXML
    public void initialize() {
        addDataGui = new AddDataGui();
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
            addDataGui.closeAddDataGui();
        } else {
            titleFieldWarning.setVisible(true);
            textTitleCannotBeEmpty.setVisible(true);
        }
    }
}
