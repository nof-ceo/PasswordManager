module com.ndtm.passwordmanager {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;
    requires spring.data.jpa;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires jakarta.persistence;

    opens com.ndtm.passwordmanager to javafx.fxml;
    exports com.ndtm.passwordmanager;
    exports com.ndtm.passwordmanager.controller;
}