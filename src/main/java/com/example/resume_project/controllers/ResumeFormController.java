package com.example.resume_project.controllers;

import com.example.resume_project.app_utils.AppStateSingleton;
import com.example.resume_project.app_utils.Util;
import com.example.resume_project.models.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import org.kordamp.ikonli.javafx.FontIcon;

import java.io.File;
import java.util.Set;

public class ResumeFormController {
    @FXML
    private TextField fullNameField;
    @FXML
    private TextField professionField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;
    @FXML
    private TextArea summaryField;
    @FXML
    private TextArea skillsField;
    @FXML
    private TextArea technicalSkillsField;
    @FXML
    private StackPane portraitChooser;
    @FXML
    private Image portraitImage;

    @FXML
    private HBox educationEntriesContainer;
    @FXML
    private HBox workEntriesContainer;

    @FXML
    private Text errorText;

    @FXML
    protected void addEducationForm() {
        HBox container = new HBox();
        container.setPrefWidth(400);
        container.setPrefHeight(224);
        container.setStyle("-fx-border-color: #0003; -fx-border-width: 1; -fx-border-radius: 5;");

        VBox formContainer = new VBox();
        formContainer.setSpacing(8);
        formContainer.setPadding(new Insets(12, 12, 12, 12));
        HBox.setHgrow(formContainer, Priority.ALWAYS);

        HBox startEndFieldsContainer = new HBox();
        startEndFieldsContainer.setSpacing(8);

        VBox startMonthContainer = new VBox();
        HBox.setHgrow(startMonthContainer, Priority.ALWAYS);
        Label startMonthLabel = new Label("Start Month");
        TextField startMonthField = new TextField();
        startMonthContainer.getChildren().add(startMonthLabel);
        startMonthContainer.getChildren().add(startMonthField);

        VBox endMonthContainer = new VBox();
        HBox.setHgrow(endMonthContainer, Priority.ALWAYS);
        Label endMonthLabel = new Label("End Month");
        TextField endMonthField = new TextField();
        endMonthContainer.getChildren().add(endMonthLabel);
        endMonthContainer.getChildren().add(endMonthField);

        startEndFieldsContainer.getChildren().add(startMonthContainer);
        startEndFieldsContainer.getChildren().add(endMonthContainer);
        formContainer.getChildren().add(startEndFieldsContainer);

        HBox credentialFieldOfStudyFieldsContainer = new HBox();
        credentialFieldOfStudyFieldsContainer.setSpacing(8);

        VBox credentialContainer = new VBox();
        HBox.setHgrow(credentialContainer, Priority.ALWAYS);
        Label credentialLabel = new Label("Degree/Credential");
        TextField credentialField = new TextField();
        credentialContainer.getChildren().add(credentialLabel);
        credentialContainer.getChildren().add(credentialField);

        VBox fieldOfStudyContainer = new VBox();
        HBox.setHgrow(fieldOfStudyContainer, Priority.ALWAYS);
        Label fieldOfStudyLabel = new Label("Field of Study");
        TextField fieldOfStudyField = new TextField();
        fieldOfStudyContainer.getChildren().add(fieldOfStudyLabel);
        fieldOfStudyContainer.getChildren().add(fieldOfStudyField);

        credentialFieldOfStudyFieldsContainer.getChildren().add(credentialContainer);
        credentialFieldOfStudyFieldsContainer.getChildren().add(fieldOfStudyContainer);
        formContainer.getChildren().add(credentialFieldOfStudyFieldsContainer);

        VBox institutionNameContainer = new VBox();
        HBox.setHgrow(institutionNameContainer, Priority.ALWAYS);
        Label institutionNameLabel = new Label("Name of Institution");
        TextField institutionNameField = new TextField();
        institutionNameContainer.getChildren().add(institutionNameLabel);
        institutionNameContainer.getChildren().add(institutionNameField);
        formContainer.getChildren().add(institutionNameContainer);

        VBox locationContainer = new VBox();
        HBox.setHgrow(locationContainer, Priority.ALWAYS);
        Label locationLabel = new Label("Location");
        TextField locationField = new TextField();
        locationContainer.getChildren().add(locationLabel);
        locationContainer.getChildren().add(locationField);
        formContainer.getChildren().add(locationContainer);

        VBox deleteContainer = new VBox();
        deleteContainer.setAlignment(Pos.CENTER);
        deleteContainer.setPadding(new Insets(0, 8, 0, 0));

        Button deleteButton = new Button();
        deleteButton.setPrefWidth(18);
        deleteButton.setMinWidth(Double.NEGATIVE_INFINITY);
        deleteButton.setStyle("-fx-background-color: #0000;");

        FontIcon deleteIcon = new FontIcon();
        deleteIcon.setIconLiteral("gmi-delete");
        deleteIcon.setIconSize(18);

        deleteButton.setGraphic(deleteIcon);
        deleteContainer.getChildren().add(deleteButton);

        container.getChildren().add(formContainer);
        container.getChildren().add(deleteContainer);

        deleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                deleteEducationForm(container);
            }
        });

        if (educationEntriesContainer.getChildren().isEmpty())
            ((HBox) educationEntriesContainer.getParent()).setSpacing(16);

        educationEntriesContainer.getChildren().add(container);
    }

    private void addObjective(VBox container) {
        HBox objective = new HBox();

        Button deleteObjectiveButton = new Button();
        deleteObjectiveButton.setAlignment(Pos.CENTER);
        deleteObjectiveButton.setPrefHeight(39);
        deleteObjectiveButton.setStyle("-fx-background-color: #0000; -fx-border-color: #0004; -fx-border-width: 1; -fx-border-radius: 3;");
        HBox.setMargin(deleteObjectiveButton, new Insets(0, 8, 0, 0));

        FontIcon deleteObjectiveIcon = new FontIcon();
        deleteObjectiveIcon.setIconLiteral("gmi-delete");
        deleteObjectiveIcon.setIconSize(16);

        deleteObjectiveButton.setGraphic(deleteObjectiveIcon);

        TextArea objectiveTextArea = new TextArea();
        objectiveTextArea.setPrefHeight(40);
        objectiveTextArea.setWrapText(true);

        deleteObjectiveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                container.requestFocus();
                container.getChildren().remove(objective);

                if (container.getChildren().isEmpty())
                    ((VBox)container.getParent()).setSpacing(0);
            }
        });

        objective.getChildren().add(deleteObjectiveButton);
        objective.getChildren().add(objectiveTextArea);

        if (container.getChildren().isEmpty())
            ((VBox)container.getParent()).setSpacing(8);

        container.getChildren().add(objective);
    }

    @FXML
    protected void addWorkForm() {
        HBox container = new HBox();
        container.setPrefWidth(450);
        container.setStyle("-fx-border-color: #0003; -fx-border-width: 1; -fx-border-radius: 5;");
        HBox.setHgrow(container, Priority.ALWAYS);

        VBox formContainer = new VBox();
        formContainer.setSpacing(8);
        formContainer.setPadding(new Insets(12, 12, 12, 12));
        HBox.setHgrow(formContainer, Priority.ALWAYS);

        HBox startEndContainer = new HBox();
        startEndContainer.setSpacing(8);

        VBox startMonthContainer = new VBox();
        HBox.setHgrow(startMonthContainer, Priority.ALWAYS);
        Label startMonthLabel = new Label("Start Month");
        TextField startMonthField = new TextField();
        startMonthContainer.getChildren().add(startMonthLabel);
        startMonthContainer.getChildren().add(startMonthField);

        VBox endMonthContainer = new VBox();
        HBox.setHgrow(endMonthContainer, Priority.ALWAYS);
        Label endMonthLabel = new Label("End Month");
        TextField endMonthField = new TextField();
        endMonthContainer.getChildren().add(endMonthLabel);
        endMonthContainer.getChildren().add(endMonthField);

        startEndContainer.getChildren().add(startMonthContainer);
        startEndContainer.getChildren().add(endMonthContainer);

        VBox positionContainer = new VBox();
        Label positionLabel = new Label("Position");
        TextField positionField = new TextField();
        positionContainer.getChildren().add(positionLabel);
        positionContainer.getChildren().add(positionField);

        HBox companyContainer = new HBox();
        companyContainer.setPadding(new Insets(0, 0, 6, 0));
        companyContainer.setSpacing(8);

        VBox companyNameContainer = new VBox();
        HBox.setHgrow(companyNameContainer, Priority.ALWAYS);
        Label companyNameLabel = new Label("Company Name");
        TextField companyNameField = new TextField();
        companyNameContainer.getChildren().add(companyNameLabel);
        companyNameContainer.getChildren().add(companyNameField);

        VBox locationContainer = new VBox();
        HBox.setHgrow(locationContainer, Priority.ALWAYS);
        Label locationLabel = new Label("Company Location");
        TextField locationField = new TextField();
        locationContainer.getChildren().add(locationLabel);
        locationContainer.getChildren().add(locationField);

        companyContainer.getChildren().add(companyNameContainer);
        companyContainer.getChildren().add(locationContainer);

        Text objectivesText = new Text("Objectives");
        objectivesText.setFill(Paint.valueOf("#0009"));
        objectivesText.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        VBox objectivesContainer = new VBox();

        VBox objectivesListContainer = new VBox();
        objectivesListContainer.setSpacing(8);

        Button addObjectiveButton = new Button("Add Objective");
        addObjectiveButton.setMaxWidth(Double.POSITIVE_INFINITY);
        addObjectiveButton.setStyle("-fx-border-color: #0004; -fx-border-width: 1; -fx-border-radius: 3; -fx-background-color: #0000; -fx-font-size: 15;");
        addObjectiveButton.setPadding(new Insets(4, 4, 4, 4));
        HBox.setHgrow(addObjectiveButton, Priority.ALWAYS);

        objectivesContainer.getChildren().add(objectivesListContainer);
        objectivesContainer.getChildren().add(addObjectiveButton);

        formContainer.getChildren().add(startEndContainer);
        formContainer.getChildren().add(positionContainer);
        formContainer.getChildren().add(companyContainer);
        formContainer.getChildren().add(objectivesText);
        formContainer.getChildren().add(objectivesContainer);

        VBox deleteEntryContainer = new VBox();
        deleteEntryContainer.setAlignment(Pos.CENTER);

        Button deleteButton = new Button();
        deleteButton.setPrefWidth(18);
        deleteButton.setStyle("-fx-background-color: #0000;");

        FontIcon deleteIcon = new FontIcon();
        deleteIcon.setIconLiteral("gmi-delete");
        deleteIcon.setIconSize(18);

        deleteButton.setGraphic(deleteIcon);
        deleteEntryContainer.getChildren().add(deleteButton);

        container.getChildren().add(formContainer);
        container.getChildren().add(deleteEntryContainer);

        addObjectiveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                addObjective(objectivesListContainer);
            }
        });

        deleteButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                deleteWorkForm(container);
            }
        });

        if (workEntriesContainer.getChildren().isEmpty())
            ((HBox) workEntriesContainer.getParent()).setSpacing(16);

        workEntriesContainer.getChildren().add(container);
    }

    @FXML
    protected void back() {
        Util.goTo("select-template-view.fxml", fullNameField.getScene());
    }

    @FXML
    protected void buildResume() {
        String fullName = fullNameField.getText();
        String profession = professionField.getText();
        String address = addressField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String summary = summaryField.getText();
        String[] skills = skillsField.getText().split("\n");
        String[] technicalSkills = technicalSkillsField.getText().split("\n");

        Education[] education = new Education[educationEntriesContainer.getChildren().size()];
        Work[] workExperience = new Work[workEntriesContainer.getChildren().size()];

        for (int i = 0; i < education.length; i++) {
            String startMonth = "", endMonth = "", credential = "", fieldOfStudy = "", institution = "", location = "";

            Node educationContainer = educationEntriesContainer.getChildren().get(i);
            Set<Node> fields = educationContainer.lookupAll(".text-field");
            int j = 0;

            for (Node node : fields) {
                TextField field = (TextField)node;

                switch (j++) {
                    case 0:
                        startMonth = field.getText();
                        break;
                    case 1:
                        endMonth = field.getText();
                        break;
                    case 2:
                        credential = field.getText();
                        break;
                    case 3:
                        fieldOfStudy = field.getText();
                        break;
                    case 4:
                        institution = field.getText();
                        break;
                    case 5:
                        location = field.getText();
                        break;
                }
            }

            education[i] = new Education(startMonth, endMonth, credential, fieldOfStudy, institution, location);
        }

        for (int i = 0; i < workExperience.length; i++) {
            String startMonth = "", endMonth = "", position = "", company = "", location = "";

            Node workContainer = workEntriesContainer.getChildren().get(i);
            Set<Node> fields = workContainer.lookupAll(".text-field");
            int j = 0;

            for (Node node : fields) {
                TextField field = (TextField)node;

                switch (j++) {
                    case 0:
                        startMonth = field.getText();
                        break;
                    case 1:
                        endMonth = field.getText();
                        break;
                    case 2:
                        position = field.getText();
                        break;
                    case 3:
                        company = field.getText();
                        break;
                    case 4:
                        location = field.getText();
                        break;
                }
            }

            Set<Node> textAreas = workContainer.lookupAll(".text-area");
            Objective[] objectives = new Objective[textAreas.size()];
            j = 0;

            for (Node node : textAreas)
                objectives[j++] = new Objective(((TextArea)node).getText());

            workExperience[i] = new Work(startMonth, endMonth, position, company, location, objectives);
        }


        Resume oldResume = AppStateSingleton.getInstance().getResume();
        Resume resume = new Resume(fullName, profession, address, phone, email, summary, workExperience,
                education, skills, technicalSkills);

        if (oldResume != null) {
            resume.setId(oldResume.getId());
            resume.setTitle(oldResume.getTitle());
        } else {
            resume.setTitle("MyResume");
        }

        if (portraitImage != null)
            resume.setAvatar(portraitImage);

        if (!isResumeComplete(resume)) {
            errorText.setText("All fields must not be blank.");
        } else if (resume.getEducation().length == 0 || resume.getWorkExperience().length == 0) {
            errorText.setText("There must be at least one education entry and one work entry.");
        } else {
            AppStateSingleton.getInstance().setResume(resume);
            Util.goTo("resume-editor-view.fxml", fullNameField.getScene());
        }
    }

    private void deleteEducationForm(Node node) {
        educationEntriesContainer.requestFocus();
        educationEntriesContainer.getChildren().remove(node);

        if (educationEntriesContainer.getChildren().isEmpty())
            ((HBox) educationEntriesContainer.getParent()).setSpacing(0);
    }

    private void deleteWorkForm(Node node) {
        workEntriesContainer.requestFocus();
        workEntriesContainer.getChildren().remove(node);

        if (workEntriesContainer.getChildren().isEmpty())
            ((HBox) workEntriesContainer.getParent()).setSpacing(0);
    }

    private void fillForm(Resume resume) {
        fullNameField.setText(resume.getName());
        professionField.setText(resume.getProfession());
        addressField.setText(resume.getAddress());
        phoneField.setText(resume.getPhone());
        emailField.setText(resume.getEmail());
        summaryField.setText(resume.getSummary());
        skillsField.setText(String.join("\n", resume.getSkills()));
        technicalSkillsField.setText(String.join("\n", resume.getTechnicalSkills()));

        if (resume.getAvatar() != null)
            setPortrait(resume.getAvatar());

        Education[] education = resume.getEducation();

        for (int i = 0; i < education.length; i++) {
            addEducationForm();

            var fields = educationEntriesContainer.getChildren().get(i).lookupAll(".text-field");
            int j = 0;

            for (Node node : fields) {
                TextField field = (TextField)node;

                switch (j++) {
                    case 0:
                        field.setText(education[i].getStart());
                        break;
                    case 1:
                        field.setText(education[i].getEnd());
                        break;
                    case 2:
                        field.setText(education[i].getCredential());
                        break;
                    case 3:
                        field.setText(education[i].getFieldOfStudy());
                        break;
                    case 4:
                        field.setText(education[i].getInstitution());
                        break;
                    case 5:
                        field.setText(education[i].getLocation());
                        break;
                }
            }
        }

        Work[] workExperience = resume.getWorkExperience();

        for (int i = 0; i < workExperience.length; i++) {
            addWorkForm();

            HBox workContainer = (HBox)workEntriesContainer.getChildren().get(i);
            Set<Node> fields = workContainer.lookupAll(".text-field");
            int j = 0;

            for (Node node : fields) {
                TextField field = (TextField)node;

                switch (j++) {
                    case 0:
                        field.setText(workExperience[i].getStart());
                        break;
                    case 1:
                        field.setText(workExperience[i].getEnd());
                        break;
                    case 2:
                        field.setText(workExperience[i].getPosition());
                        break;
                    case 3:
                        field.setText(workExperience[i].getCompany());
                        break;
                    case 4:
                        field.setText(workExperience[i].getLocation());
                        break;
                }
            }

            var formContainerChildren = ((VBox)workContainer.getChildren().get(0)).getChildren();
            VBox objectivesContainer = (VBox)formContainerChildren.get(formContainerChildren.size() - 1);
            VBox objectivesListContainer = (VBox)objectivesContainer.getChildren().get(0);

            for (j = 0; j < workExperience[i].getObjectives().length; j++)
                addObjective(objectivesListContainer);

            Set<Node> textAreas = workContainer.lookupAll(".text-area");
            j = 0;

            for (Node node : textAreas) {
                TextArea textArea = (TextArea)node;
                textArea.setText(workExperience[i].getObjectives()[j++].getText());
            }
        }
    }

    @FXML
    protected void handleChooseImage() {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif")
        );

        File selectedFile = chooser.showOpenDialog(null);

        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            setPortrait(image);
        }
    }

    @FXML
    public void initialize() {
        if (AppStateSingleton.getInstance().getResume() != null)
            fillForm(AppStateSingleton.getInstance().getResume());

        if (AppStateSingleton.getInstance().getTemplate() < 2)
            ((HBox)portraitChooser.getParent()).getChildren().remove(portraitChooser);
    }

    private boolean isResumeComplete(Resume resume) {
        if (resume.getName().trim().isEmpty() || resume.getProfession().trim().isEmpty() ||
            resume.getAddress().trim().isEmpty() ||resume.getPhone().trim().isEmpty() ||
            resume.getEmail().trim().isEmpty() || resume.getSummary().trim().isEmpty() ||
            String.join("\n", resume.getSkills()).trim().isEmpty() ||
            String.join("\n", resume.getTechnicalSkills()).trim().isEmpty())
            return false;

        for (int i = 0; i < resume.getWorkExperience().length; i++) {
            Work work = resume.getWorkExperience()[i];

            if (work.getStart().trim().isEmpty() || work.getEnd().trim().isEmpty() ||
                work.getPosition().trim().isEmpty() || work.getCompany().trim().isEmpty() ||
                work.getLocation().trim().isEmpty())
                return false;

            for (int j = 0; j < work.getObjectives().length; j++)
                if (work.getObjectives()[j].getText().trim().isEmpty())
                    return false;
        }

        for (int i = 0; i < resume.getEducation().length; i++) {
            Education education = resume.getEducation()[i];

            if (education.getStart().trim().isEmpty() || education.getEnd().trim().isEmpty() ||
                education.getCredential().trim().isEmpty() || education.getFieldOfStudy().trim().isEmpty() ||
                education.getInstitution().trim().isEmpty() || education.getLocation().trim().isEmpty())
                return false;
        }

        return true;
    }

    private void setPortrait(Image image) {
        ImageView view = Util.createAvatar(image, 178);

        if (portraitChooser.getChildren().size() > 1)
            portraitChooser.getChildren().remove(0);

        portraitChooser.getChildren().add(0, view);
        portraitImage = image;
    }
}