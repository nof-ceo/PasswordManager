package com.ndtm.passwordmanager.GUI;

import com.ndtm.passwordmanager.PasswordManagerApplication;
import com.ndtm.passwordmanager.controller.AddDataMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AddDataGui extends StageManager {
    private static final Stage stage = new Stage();

    private AddDataMenuController addDataMenuController = new AddDataMenuController();

    Scene scene;

    static {
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.APPLICATION_MODAL);
    }

    public static void setPointOfWindow(double x, double y) {
        stage.setX(x);
        stage.setY(y);
    }

    public void showAddDataGui() {
        if (!stage.isShowing()) {
            try {
                decreaseBrightness();

                FXMLLoader fxmlLoader = new FXMLLoader(PasswordManagerApplication.class.getResource("addDataMenu.fxml"));

                scene = new Scene(fxmlLoader.load());
                scene.setFill(Color.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void showEditDataGui() {
        if(!stage.isShowing()) {
            try {
                decreaseBrightness();

                FXMLLoader fxmlLoader = new FXMLLoader(PasswordManagerApplication.class.getResource("addDataMenu.fxml"));

                scene = new Scene(fxmlLoader.load());
                scene.setFill(Color.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void closeAddDataGui() {
        increaseBrightness();

        stage.close();
    }

    private void decreaseBrightness() {
        ColorAdjust setBrightness = new ColorAdjust();
        setBrightness.setBrightness(-0.7);

        MainGui.group.setEffect(setBrightness);
    }

    private void increaseBrightness() {
        ColorAdjust setBrightness = new ColorAdjust();
        setBrightness.setBrightness(0);

        MainGui.group.setEffect(setBrightness);
    }
}
