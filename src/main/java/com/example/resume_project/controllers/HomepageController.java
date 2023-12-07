package com.example.resume_project.controllers;

import com.example.resume_project.app_utils.*;
import com.example.resume_project.models.ResumeSQL;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.kordamp.ikonli.javafx.FontIcon;

import java.util.List;

public class HomepageController {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Pane pane;
    @FXML
    private VBox resumeListContainer;

    List<ResumeSQL> resumeList;

    @FXML
    private void initialize() {
        resumeList = DB.getResumes();
        renderResumeItems();
    }

    private void renderResumeItems() {
        List<Node> deleteConfirmationNodes = pane.getChildren().subList(1, pane.getChildren().size());
        deleteConfirmationNodes.clear();

        resumeListContainer.getChildren().clear();

        if (resumeList.size() > 0) {
            int numRows = (int)Math.ceil(resumeList.size() / 6.0);

            for (int i = 0; i < numRows; i++) {
                HBox row = new HBox();
                row.setSpacing(28);

                for (int j = 0; j < 6; j++) {
                    if (i * 6 + j < resumeList.size()) {
                        ResumeSQL resume = resumeList.get(i * 6 + j);
                        Image snapshot = resume.getSnapshot();

                        VBox resumeContainer = new VBox();
                        resumeContainer.setSpacing(4);
                        resumeContainer.setAlignment(Pos.CENTER);

                        VBox snapshotViewContainer = new VBox();
                        snapshotViewContainer.setStyle("-fx-border-width: 1; -fx-border-color: #0005; -fx-border-radius: 10;");

                        ImageView view = new ImageView(snapshot);
                        view.setPreserveRatio(true);
                        view.setFitWidth(175);

                        Rectangle rectangle = new Rectangle(175, snapshot.getHeight() / snapshot.getWidth() * 175);
                        rectangle.setArcWidth(20);
                        rectangle.setArcHeight(20);
                        view.setClip(rectangle);

                        snapshotViewContainer.getChildren().add(view);

                        Text titleText = new Text(resume.getTitle());
                        titleText.setStyle("-fx-font-size: 16");

                        HBox actionsContainer = new HBox();
                        actionsContainer.setMaxWidth(Double.POSITIVE_INFINITY);
                        actionsContainer.setStyle("-fx-border-width: 1; -fx-border-color: #0004; -fx-border-radius: 15;");
                        HBox.setHgrow(actionsContainer, Priority.ALWAYS);

                        Button viewButton = new Button();
                        viewButton.setMaxWidth(Double.POSITIVE_INFINITY);
                        viewButton.setStyle("-fx-background-color: #0000; -fx-border-width: 0 1 0 0; -fx-border-color: #0004");
                        HBox.setHgrow(viewButton, Priority.ALWAYS);

                        FontIcon viewIcon = new FontIcon();
                        viewIcon.setIconLiteral("gmi-visibility");
                        viewIcon.setIconSize(16);

                        viewButton.setGraphic(viewIcon);
                        viewButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                AppStateSingleton instance = AppStateSingleton.getInstance();
                                instance.setTemplate(resume.getTemplate());
                                instance.setResume(resume);
                                instance.setResumeStyleSettings(resume.getSettings());
                                instance.setDefaultStyleSettings(resume.getSettings());

                                Util.goTo("resume-editor-view.fxml", resumeListContainer.getScene());
                            }
                        });

                        Button editButton = new Button();
                        editButton.setMaxWidth(Double.POSITIVE_INFINITY);
                        editButton.setStyle("-fx-background-color: #0000; -fx-border-width: 0 1 0 0; -fx-border-color: #0004");
                        HBox.setHgrow(editButton, Priority.ALWAYS);

                        FontIcon editIcon = new FontIcon();
                        editIcon.setIconLiteral("gmi-edit");
                        editIcon.setIconSize(16);

                        editButton.setGraphic(editIcon);
                        editButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent mouseEvent) {
                                AppStateSingleton instance = AppStateSingleton.getInstance();
                                instance.setTemplate(resume.getTemplate());
                                instance.setResume(resume);
                                instance.setResumeStyleSettings(resume.getSettings());
                                instance.setDefaultStyleSettings(resume.getSettings());

                                Util.goTo("select-template-view.fxml", resumeListContainer.getScene());
                            }
                        });

                        Button deleteButton = new Button();
                        deleteButton.setMaxWidth(Double.POSITIVE_INFINITY);
                        deleteButton.setStyle("-fx-background-color: #0000;");
                        HBox.setHgrow(deleteButton, Priority.ALWAYS);

                        FontIcon deleteIcon = new FontIcon();
                        deleteIcon.setIconLiteral("gmi-delete");
                        deleteIcon.setIconSize(16);

                        deleteButton.setGraphic(deleteIcon);
                        addDeleteConfirmation(deleteButton, resume);

                        actionsContainer.getChildren().addAll(viewButton, editButton, deleteButton);

                        resumeContainer.getChildren().addAll(snapshotViewContainer, titleText, actionsContainer);

                        row.getChildren().add(resumeContainer);
                    }
                }

                resumeListContainer.getChildren().add(row);
            }
        } else {
            Text noResumesText = new Text("No resumes");
            noResumesText.setFill(Color.valueOf("#0005"));
            noResumesText.setStyle("-fx-font-size: 18;");

            pane.minHeightProperty().bind(scrollPane.heightProperty().subtract(16));
            resumeListContainer.setAlignment(Pos.CENTER);
            resumeListContainer.prefHeightProperty().bind(scrollPane.heightProperty().subtract(16));
            resumeListContainer.prefWidthProperty().bind(scrollPane.widthProperty().subtract(16));
            resumeListContainer.getChildren().add(noResumesText);
        }
    }

    private void addDeleteConfirmation(Button deleteButton, ResumeSQL resume) {
        deleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                deleteButton.setOnMouseClicked(null);

                EventHandler<MouseEvent> handler = this;
                Point2D bounds = pane.sceneToLocal(
                        deleteButton.localToScene(deleteButton.getLayoutX(), deleteButton.getLayoutY())
                );

                VBox deleteConfirmationContainer = new VBox();
                deleteConfirmationContainer.setStyle("-fx-background-color: #FFF; -fx-border-width: 1; -fx-border-color: #0004;");
                deleteConfirmationContainer.setPadding(new Insets(12, 12, 12, 12));
                deleteConfirmationContainer.setMaxWidth(150);
                deleteConfirmationContainer.setMaxHeight(Double.NEGATIVE_INFINITY);
                deleteConfirmationContainer.setLayoutX(bounds.getX() - 235.5);
                deleteConfirmationContainer.setLayoutY(bounds.getY() - 190);

                VBox deleteConfirmationTextContainer = new VBox();
                deleteConfirmationTextContainer.setPadding(new Insets(0, 0, 8, 0));

                Text deleteConfirmationText = new Text("Are you sure you want to delete this resume?");
                deleteConfirmationText.setStyle("-fx-font-size: 16;");
                deleteConfirmationText.setWrappingWidth(150);

                deleteConfirmationTextContainer.getChildren().add(deleteConfirmationText);

                HBox deleteConfirmationActionsContainer = new HBox();
                deleteConfirmationActionsContainer.setSpacing(8);
                deleteConfirmationActionsContainer.setAlignment(Pos.CENTER_RIGHT);

                Button cancelDeleteButton = new Button("Cancel");
                cancelDeleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        pane.getChildren().remove(deleteConfirmationContainer);
                        deleteButton.setOnMouseClicked(handler);
                    }
                });

                Button confirmDeleteButton = new Button("Delete");
                confirmDeleteButton.setStyle("-fx-background-color: #B00");
                confirmDeleteButton.setTextFill(Color.valueOf("#FFF"));
                confirmDeleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        Node resumeContainer = deleteButton.getParent().getParent();
                        Pane row = (Pane)resumeContainer.getParent();

                        pane.getChildren().remove(deleteConfirmationContainer);

                        resumeList.remove(resume);
                        DB.deleteResume(resume);
                        row.getChildren().remove(resumeContainer);

                        renderResumeItems();
                    }
                });

                deleteConfirmationActionsContainer.getChildren().addAll(cancelDeleteButton,
                        confirmDeleteButton);

                deleteConfirmationContainer.getChildren().addAll(deleteConfirmationTextContainer,
                        deleteConfirmationActionsContainer);

                pane.getChildren().addAll(deleteConfirmationContainer);
            }
        });
    }

    @FXML
    protected void handleCreateResumeClick() {
        AppStateSingleton instance = AppStateSingleton.getInstance();
        instance.setTemplate(-1);
        instance.setResume(null);
//        instance.setResume(Util.getExampleResume());  // Uncomment to see the resume builder use a realistic example;

        Util.goTo("select-template-view.fxml", resumeListContainer.getScene());
    }
}
