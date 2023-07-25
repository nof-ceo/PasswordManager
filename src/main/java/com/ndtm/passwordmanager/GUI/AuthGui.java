package com.ndtm.passwordmanager.GUI;

public class AuthGui extends StageManager {

    public static void setPointOfWindow(double x, double y) {
        AuthGui.currentStage.setX(x);
        AuthGui.currentStage.setY(y);
    }

    public static void closeAuthWindow() {
        AuthGui.currentStage.hide();
    }
}
