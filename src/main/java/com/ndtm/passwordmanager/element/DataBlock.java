package com.ndtm.passwordmanager.element;

import com.ndtm.passwordmanager.GUI.MainGui;
import com.ndtm.passwordmanager.controller.PasswordManagerMainController;
import com.ndtm.passwordmanager.manage.SavedData;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class DataBlock extends MainGui{
    Pane pane;
    Rectangle rectangle;
    Label title;
    ImageView favicon;
    Label login;
    Label savedLogin;
    Label password;
    Label savedPassword;
    ContextMenu contextMenu;
    MenuItem edit;
    MenuItem delete;

    SavedData addingData;

    Font font = Font.loadFont("file:src/main/resources/com/ndtm/passwordManager/Fonts/ArialRoundedBold.otf", 13);

    PasswordManagerMainController passwordManagerMainController = new PasswordManagerMainController();
    public DataBlock(SavedData savedData, int y) {
        addingData = savedData;

        rectangle = new Rectangle(283, 77);
        rectangle.setArcWidth(50);
        rectangle.setArcHeight(50);
        rectangle.setOpacity(0.46);
        rectangle.setX(0);
        rectangle.setY(0);
        rectangle.setStyle("-fx-fill: #c5b8ff;");

        title = new Label(savedData.getSiteTitle());
        title.setLayoutX(40);
        title.setLayoutY(10);
        title.setPrefSize(82.5, 16);
        title.setFont(font);
        title.setTextFill(Paint.valueOf("#686868"));

        favicon = new ImageView(passwordManagerMainController.parseFavicon(savedData.getSiteUrl()));
        favicon.setLayoutX(20);
        favicon.setLayoutY(10);
        favicon.setFitWidth(20);
        favicon.setFitHeight(20);

        login = new Label("Login: ");
        login.setLayoutX(20);
        login.setLayoutY(27);
        login.setTextFill(Paint.valueOf("#000000"));
        login.setFont(font);

        savedLogin = new Label(savedData.getLogin());
        savedLogin.setLayoutX(65);
        savedLogin.setLayoutY(27);
        savedLogin.setPrefSize(82.5, 16);
        savedLogin.setTextFill(Paint.valueOf("#686868"));
        savedLogin.setFont(font);

        password = new Label("Password: ");
        password.setLayoutX(20);
        password.setLayoutY(45);
        password.setTextFill(Paint.valueOf("#000000"));
        password.setFont(font);

        savedPassword = new Label("• ");
        savedPassword.setText(savedPassword.getText().repeat(savedData.getPassword().length()));
        savedPassword.setLayoutX(95);
        savedPassword.setLayoutY(45);
        savedPassword.setPrefSize(82.5, 16);
        savedPassword.setTextFill(Paint.valueOf("#686868"));
        savedPassword.setFont(font);

        pane = new Pane(rectangle, title, favicon, login, savedLogin, password, savedPassword);
        pane.setTranslateX(270);
        pane.setLayoutY(40 + y);
        pane.setPrefSize(rectangle.getWidth(), rectangle.getHeight());
        pane.setStyle("-fx-background-color: transparent;");
        pane.setId(group.getChildren().size() + "");

        contextMenu = new ContextMenu();
        edit = new MenuItem("Edit");
        delete = new MenuItem("Delete");
        contextMenu.getItems().add(edit);
        contextMenu.getItems().add(delete);
        contextMenu.setPrefSize(50, 15);

        pane.setOnContextMenuRequested(event -> contextMenu.show(pane, event.getScreenX(), event.getScreenY()));
    }


    public Pane getPane() {
        return pane;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Label getTitle() {
        return title;
    }

    public ImageView getFavicon() {
        return favicon;
    }

    public Label getLogin() {
        return login;
    }

    public Label getSavedLogin() {
        return savedLogin;
    }

    public Label getPassword() {
        return password;
    }

    public Label getSavedPassword() {
        return savedPassword;
    }

    public MenuItem getEdit() {
        return edit;
    }

    public MenuItem getDelete() {
        return delete;
    }

    public SavedData getAddingData() {
        return addingData;
    }

    public void setAddingData(SavedData addingData) {
        this.addingData = addingData;
    }

}
