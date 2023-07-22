module com.ndtm.passwordmanager {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.ndtm.passwordmanager to javafx.fxml;
    exports com.ndtm.passwordmanager;
    exports com.ndtm.passwordmanager.controller;
}