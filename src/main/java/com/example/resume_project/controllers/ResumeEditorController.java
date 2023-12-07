package com.example.resume_project.controllers;

import com.example.resume_project.app_utils.*;
import com.example.resume_project.models.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.graphics.image.*;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

import static com.example.resume_project.app_utils.Util.imageToBufferedImage;

public class ResumeEditorController {
    @FXML
    private VBox resumeView;

    @FXML
    private ComboBox<String> fontPicker;
    @FXML
    private ColorPicker color1;
    @FXML
    private ColorPicker color2;
    @FXML
    private ColorPicker color3;
    @FXML
    private ColorPicker color4;
    @FXML
    private ColorPicker color5;
    @FXML
    private ColorPicker color6;

    @FXML
    private Text feedbackText;
    @FXML
    private TextField titleField;
    @FXML
    private HBox saveDoneContainer;
    private Button doneButton;

    private AppStateSingleton appState;
    private Timer timer = new Timer();

    @FXML
    protected void back() {
        Util.goTo("resume-form-view.fxml", resumeView.getScene());
    }

    @FXML
    protected void done() {
        Util.goTo("home-view.fxml", resumeView.getScene());
    }

    @FXML
    protected void download() throws IOException {
        if (titleField.getText().trim().isEmpty()) {
            showFeedbackMessage("Title must not be blank", true);
        } else {
            Node resumeNode = resumeView.getChildren().get(0);
            resumeNode.setScaleX(1056.0 / ResumeTemplates.RESUME_HEIGHT);
            resumeNode.setScaleY(1056.0 / ResumeTemplates.RESUME_HEIGHT);

            WritableImage image = resumeNode.snapshot(new SnapshotParameters(), null);

            resumeNode.setScaleX(1);
            resumeNode.setScaleY(1);

            PDDocument document = new PDDocument();
            PDPage page = new PDPage();

            document.addPage(page);

            float pageWidth = page.getMediaBox().getWidth();
            float pageHeight = page.getMediaBox().getHeight();

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            PDImageXObject pdImage = LosslessFactory.createFromImage(document, imageToBufferedImage(image));

            contentStream.drawImage(pdImage, 0, 0, pageWidth, pageHeight);
            contentStream.close();

            DirectoryChooser chooser = new DirectoryChooser();
            chooser.setTitle("Save PDF");
            File selectedDirectory = chooser.showDialog(resumeView.getScene().getWindow());

            if (selectedDirectory != null) {
                String outputPath = String.format("%s%s%s.pdf", selectedDirectory.getAbsolutePath(),
                        File.separator, titleField.getText());

                PDDocumentInformation info = new PDDocumentInformation();
                info.setModificationDate(Calendar.getInstance());

                document.setDocumentInformation(info);
                document.save(outputPath);
                document.close();

                showFeedbackMessage("Successfully saved resume to PDF", false);
            }
        }
    }

    private ResumeStyleSettings getSettingsFromFields() {
        String[] colors = {
                Util.colorToHexString(color1.getValue()),
                Util.colorToHexString(color2.getValue()),
                Util.colorToHexString(color3.getValue()),
                Util.colorToHexString(color4.getValue()),
                Util.colorToHexString(color5.getValue()),
                Util.colorToHexString(color6.getValue())
        };

        return new ResumeStyleSettings(fontPicker.getValue(), colors);
    }

    @FXML
    public void initialize() {
        appState = AppStateSingleton.getInstance();

        Resume resume = appState.getResume();

        fontPicker.setItems(FXCollections.observableList(Font.getFamilies()));

        titleField.setText(resume.getTitle());
        titleField.textProperty().addListener((observable, oldValue, newValue) -> resume.setTitle(newValue));

        doneButton = (Button)saveDoneContainer.getChildren().get(1);

        if (!(resume.getId() > 0))
            saveDoneContainer.getChildren().remove(doneButton);

        if (appState.getTemplate() == 1)
            ((Pane)color6.getParent().getParent()).getChildren().remove(color6.getParent());

        updateSettingsFields(appState.getResumeStyleSettings());
        renderResume();
    }

    @FXML
    protected void renderResume() {
        appState.setResumeStyleSettings(getSettingsFromFields());

        Node resumeNode;

        switch (appState.getTemplate()) {
            case 1:
                resumeNode = ResumeTemplates.buildTemplate2(appState.getResume(), appState.getResumeStyleSettings());
                break;
            case 2:
                resumeNode = ResumeTemplates.buildTemplate3(appState.getResume(), appState.getResumeStyleSettings());
                break;
            default:
                resumeNode = ResumeTemplates.buildTemplate1(appState.getResume(), appState.getResumeStyleSettings());
                break;
        }

        resumeView.getChildren().clear();
        resumeView.getChildren().add(resumeNode);
    }

    @FXML
    protected void reset() {
        updateSettingsFields(appState.getDefaultStyleSettings());
        renderResume();
    }

    @FXML
    protected void saveToDB() {
        if (titleField.getText().trim().isEmpty()) {
            showFeedbackMessage("Title must not be blank", true);
        } else {
            Node resumeNode = resumeView.getChildren().get(0);
            WritableImage snapshot = resumeNode.snapshot(new SnapshotParameters(), null);

            try {
                if (appState.getResume().getId() > 0) {
                    DB.updateResume(appState.getResume(), appState.getResumeStyleSettings(), snapshot);
                    showFeedbackMessage("Resume successfully updated", false);
                } else {
                    DB.createResume(appState.getResume(), appState.getResumeStyleSettings(), snapshot);
                    showFeedbackMessage("Resume successfully created", false);
                    saveDoneContainer.getChildren().add(doneButton);
                }

                appState.setDefaultStyleSettings(appState.getResumeStyleSettings());
            } catch (SQLException e) {
                showFeedbackMessage("Failed to save resume", true);
            }
        }
    }

    private void showFeedbackMessage(String message, boolean error) {
        timer.cancel();

        feedbackText.setText(message);
        feedbackText.getParent().setVisible(true);
        feedbackText.getParent().setStyle("-fx-background-radius: 5; -fx-background-color: " + (error ? "#F00" : "#0B0"));

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                feedbackText.getParent().setVisible(false);
            }
        }, 7000L);
    }

    private void updateSettingsFields(ResumeStyleSettings settings) {
        fontPicker.setValue(settings.getFont());
        color1.setValue(Color.valueOf(settings.getColor1()));
        color2.setValue(Color.valueOf(settings.getColor2()));
        color3.setValue(Color.valueOf(settings.getColor3()));
        color4.setValue(Color.valueOf(settings.getColor4()));
        color5.setValue(Color.valueOf(settings.getColor5()));

        if (color6 != null)
            color6.setValue(Color.valueOf(settings.getColor6()));
    }
}
