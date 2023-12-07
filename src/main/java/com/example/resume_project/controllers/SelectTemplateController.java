package com.example.resume_project.controllers;

import com.example.resume_project.app_utils.AppStateSingleton;
import com.example.resume_project.app_utils.Util;
import com.example.resume_project.models.ResumeStyleSettings;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class SelectTemplateController {
    @FXML
    private HBox templateList;

    @FXML
    protected void initialize() {
        Node[] templates = templateList.getChildren().toArray(Node[]::new);
        AppStateSingleton appState = AppStateSingleton.getInstance();

        for (int i = 0; i < templates.length; i++) {
            VBox template = (VBox)templates[i];
            template.setCursor(Cursor.HAND);

            ImageView view = (ImageView)template.getChildren().get(0);
            Rectangle clip = new Rectangle(275, view.getImage().getHeight() / view.getImage().getWidth() * 275);
            clip.setArcWidth(20);
            clip.setArcHeight(20);
            view.setClip(clip);

            template.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    for (int j = 0; j < templates.length; j++) {
                        if (template == templates[j]) {
                            if (j != appState.getTemplate()) {
                                appState.setTemplate(j);
                                appState.setDefaultStyleSettings(getDefaultResumeStyle(appState.getTemplate()));
                                appState.setResumeStyleSettings(getDefaultResumeStyle(appState.getTemplate()));

                                templates[j].setOpacity(1);
                                templates[j].setStyle("-fx-border-width: 3; -fx-border-color: #07F; -fx-border-radius: 10;");
                            }
                        } else {
                            templates[j].setOpacity(0.55);
                            templates[j].setStyle("");
                        }
                    }
                }
            });
        }

        if (appState.getTemplate() >= 0) {
            templates[appState.getTemplate()].setOpacity(1);
            templates[appState.getTemplate()].setStyle("-fx-border-width: 3; -fx-border-color: #07F; -fx-border-radius: 10;");
        }
    }

    private ResumeStyleSettings getDefaultResumeStyle(int template) {
        ResumeStyleSettings settings = new ResumeStyleSettings(
                "Tahoma",
                new String[] { "#FFF", "#000", "#A30", "#0009", "#FFF", "#CCC" }
        );

        switch (template) {
            case 1:
                settings.setFont("Segoe UI");
                settings.setColor3("#0A3");
                settings.setColor4("#0009");
                settings.setColor5("#0003");
                break;
            case 2:
                settings.setFont("Georgia");
                settings.setColor3("#C55");
                settings.setColor4("#34678C");
                settings.setColor5("#677B8A");
                break;
        }

        return settings;
    }

    @FXML
    protected void back() {
        Util.goTo("home-view.fxml", templateList.getScene());
    }

    @FXML
    protected void next() {
        if (AppStateSingleton.getInstance().getTemplate() >= 0)
            Util.goTo("resume-form-view.fxml", templateList.getScene());
    }
}
