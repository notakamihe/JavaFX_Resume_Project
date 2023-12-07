package com.example.resume_project;

import com.example.resume_project.app_utils.DB;
import com.example.resume_project.app_utils.Util;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DB.setConnection();

        Scene scene = new Scene(new Parent() {});
        Util.goTo("home-view.fxml", scene);

        if (System.getProperty("os.name").toLowerCase().startsWith("windows"))
            stage.setMaximized(true);
        else
            stage.setFullScreen(true);

        stage.setTitle("Resume Builder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}