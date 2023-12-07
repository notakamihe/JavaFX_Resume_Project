module com.example.resume_project {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.pdfbox;
    requires java.desktop;
    requires java.sql;
    requires mysql.connector.java;

    opens com.example.resume_project to javafx.fxml;
    exports com.example.resume_project;
    exports com.example.resume_project.models;
    opens com.example.resume_project.models to javafx.fxml;
    exports com.example.resume_project.controllers;
    opens com.example.resume_project.controllers to javafx.fxml;
    exports com.example.resume_project.app_utils;
    opens com.example.resume_project.app_utils to javafx.fxml;
}