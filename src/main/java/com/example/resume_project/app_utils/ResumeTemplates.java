package com.example.resume_project.app_utils;

import com.example.resume_project.models.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.transform.Translate;
import org.kordamp.ikonli.javafx.FontIcon;

public class ResumeTemplates {
    public static final double RESUME_HEIGHT = 780;

    public static Node buildTemplate1(Resume resume, ResumeStyleSettings settings) {
        HBox resumeContainer = new HBox();
        String fontFamilyStyle = String.format("-fx-font-family: \"%s\"", settings.getFont());
        resumeContainer.setPrefHeight(RESUME_HEIGHT);
        resumeContainer.setPrefWidth((8.5 / 11.0) * RESUME_HEIGHT);
        resumeContainer.setMaxWidth((8.5 / 11.0) * RESUME_HEIGHT);
        resumeContainer.setStyle("fx-border-width: 1; -fx-border-color: #0004; " + fontFamilyStyle);

        VBox lPanel = new VBox();
        lPanel.setPrefWidth(175);
        lPanel.setMinWidth(175);
        lPanel.setStyle(String.format("-fx-background-color: %s;", settings.getColor3()));

        VBox nameRoleInfoContainer = new VBox();
        nameRoleInfoContainer.setSpacing(4);
        nameRoleInfoContainer.setPadding(new Insets(8, 10, 8, 10));

        Text nameText = new Text(resume.getName());
        nameText.setFill(Color.valueOf(settings.getColor5()));
        nameText.wrappingWidthProperty().bind(lPanel.widthProperty());
        nameText.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

        Text roleText = new Text(resume.getProfession());
        roleText.setFill(Color.valueOf(settings.getColor5()));
        roleText.wrappingWidthProperty().bind(lPanel.widthProperty());
        roleText.setStyle("-fx-font-size: 14;");

        nameRoleInfoContainer.getChildren().addAll(autoWrap(nameText), autoWrap(roleText));

        VBox personalInfoSkillsContainer = new VBox();

        VBox personalInfoTextContainer = new VBox();
        personalInfoTextContainer.setStyle("-fx-background-color: #0007;");
        personalInfoTextContainer.setPadding(new Insets(4, 10, 4, 10));

        Text personalInfoText = new Text("Personal Info");
        personalInfoText.setFill(Color.valueOf(settings.getColor5()));
        personalInfoText.setStyle("-fx-font-size: 13; -fx-font-weight: bold;");

        personalInfoTextContainer.getChildren().add(personalInfoText);

        VBox personalInfoContainer = new VBox();
        personalInfoContainer.setSpacing(2);
        personalInfoContainer.setPadding(new Insets(2, 10, 6, 10));

        VBox addressContainer = new VBox();
        addressContainer.setStyle("-fx-font-size: 12;");

        Text addressText = new Text("Address");
        addressText.setFill(Color.valueOf(settings.getColor5()));
        addressText.setStyle("-fx-font-weight: bold;");
        VBox.setMargin(addressText, new Insets(4, 0, 4, 0));

        Text address = new Text(resume.getAddress());
        address.setFill(Color.valueOf(settings.getColor5()));

        addressContainer.getChildren().addAll(addressText, autoWrap(address));

        VBox phoneContainer = new VBox();
        phoneContainer.setStyle("-fx-font-size: 12;");

        Text phoneText = new Text("Phone Number");
        phoneText.setFill(Color.valueOf(settings.getColor5()));
        phoneText.setStyle("-fx-font-weight: bold;");
        VBox.setMargin(phoneText, new Insets(4, 0, 4, 0));

        Text phone = new Text(resume.getPhone());
        phone.setFill(Color.valueOf(settings.getColor5()));

        phoneContainer.getChildren().addAll(phoneText, autoWrap(phone));

        VBox emailContainer = new VBox();
        emailContainer.setStyle("-fx-font-size: 12;");

        Text emailText = new Text("Email Address");
        emailText.setFill(Color.valueOf(settings.getColor5()));
        emailText.setStyle("-fx-font-weight: bold;");
        VBox.setMargin(emailText, new Insets(4, 0, 4, 0));

        Text email = new Text(resume.getEmail());
        email.setFill(Color.valueOf(settings.getColor5()));

        emailContainer.getChildren().addAll(emailText, autoWrap(email));

        personalInfoContainer.getChildren().addAll(addressContainer, phoneContainer, emailContainer);

        VBox skillsTextContainer = new VBox();
        skillsTextContainer.setStyle("-fx-background-color: #0007;");
        skillsTextContainer.setPadding(new Insets(4, 10, 4, 10));

        Text skillsText = new Text("Skills");
        skillsText.setFill(Color.valueOf(settings.getColor5()));
        skillsText.setStyle("-fx-font-size: 13; -fx-font-weight: bold;");

        skillsTextContainer.getChildren().addAll(skillsText);

        VBox skillsListContainer = new VBox();
        skillsListContainer.setSpacing(6);
        skillsListContainer.setPadding(new Insets(6, 10, 6, 10));

        for (int i = 0; i < resume.getSkills().length; i++) {
            Text skill = new Text(resume.getSkills()[i]);
            skill.setFill(Color.valueOf(settings.getColor5()));
            skill.setStyle("-fx-font-size: 12;");

            skillsListContainer.getChildren().addAll(autoWrap(skill));
        }

        VBox technicalSkillsTextContainer = new VBox();
        technicalSkillsTextContainer.setStyle("-fx-background-color: #0007;");
        technicalSkillsTextContainer.setPadding(new Insets(6, 10, 6, 10));

        Text technicalSkillsText = new Text("Technical Skills");
        technicalSkillsText.setFill(Color.valueOf(settings.getColor5()));
        technicalSkillsText.setStyle("-fx-font-size: 13; -fx-font-weight: bold;");

        technicalSkillsTextContainer.getChildren().addAll(technicalSkillsText);

        VBox technicalSkillsListContainer = new VBox();
        technicalSkillsListContainer.setSpacing(6);
        technicalSkillsListContainer.setPadding(new Insets(6, 10, 6, 10));

        for (int i = 0; i < resume.getTechnicalSkills().length; i++) {
            Text technicalSkill = new Text(resume.getTechnicalSkills()[i]);
            technicalSkill.setFill(Color.valueOf(settings.getColor5()));
            technicalSkill.setStyle("-fx-font-size: 12;");

            technicalSkillsListContainer.getChildren().addAll(autoWrap(technicalSkill));
        }

        personalInfoSkillsContainer.getChildren().addAll(personalInfoTextContainer, personalInfoContainer,
                skillsTextContainer, skillsListContainer, technicalSkillsTextContainer, technicalSkillsListContainer);

        lPanel.getChildren().addAll(nameRoleInfoContainer, personalInfoSkillsContainer);

        VBox rPanel = new VBox();
        rPanel.setStyle(String.format("-fx-background-color: %s", settings.getColor1()));
        HBox.setHgrow(rPanel, Priority.ALWAYS);

        VBox summaryContainer = new VBox();
        summaryContainer.setStyle(("-fx-border-width: 0 0 1 0; -fx-border-color: " + settings.getColor3() + ";"));
        summaryContainer.setSpacing(4);
        summaryContainer.setPadding(new Insets(8, 8, 8, 8));

        Text professionalSummaryText = new Text("Professional Summary");
        professionalSummaryText.setFill(Color.valueOf(settings.getColor4()));
        professionalSummaryText.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");

        Text summaryText = new Text(resume.getSummary());
        summaryText.setStyle("-fx-font-size: 11;");
        summaryText.setFill(Color.valueOf(settings.getColor2()));

        summaryContainer.getChildren().addAll(professionalSummaryText, autoWrap(summaryText));

        VBox workExperienceContainer = new VBox();
        workExperienceContainer.setPadding(new Insets(12, 12, 12, 12));

        VBox workExperienceTextContainer = new VBox();
        workExperienceTextContainer.setStyle("-fx-border-width: 0 0 1 0; -fx-border-color: " + settings.getColor6() + ";");
        workExperienceTextContainer.setPadding(new Insets(0, 0, 4, 0));

        Text workExperienceText = new Text("Experience");
        workExperienceText.setFill(Color.valueOf(settings.getColor3()));
        workExperienceText.setStyle("-fx-font-size: 16; -fx-font-weight: bold");

        workExperienceTextContainer.getChildren().add(workExperienceText);

        VBox workExperienceListContainer = new VBox();
        workExperienceListContainer.setSpacing(8);
        workExperienceListContainer.setPadding(new Insets(8, 0, 0, 0));

        Work[] workExperience = resume.getWorkExperience();

        for (int i = 0; i < workExperience.length; i++) {
            HBox workContainer = new HBox();
            workContainer.setSpacing(16);

            VBox startEndInfoContainer = new VBox();
            startEndInfoContainer.setStyle("-fx-font-size: 12;");
            startEndInfoContainer.setMinWidth(85);

            Text startMonthText = new Text(workExperience[i].getStart() + " to");
            startMonthText.setFill(Color.valueOf(settings.getColor2()));

            Text endMonthText = new Text(workExperience[i].getEnd());
            endMonthText.setFill(Color.valueOf(settings.getColor2()));

            startEndInfoContainer.getChildren().addAll(startMonthText, endMonthText);

            VBox jobInfoContainer = new VBox();

            Text positionText = new Text(workExperience[i].getPosition());
            positionText.setFill(Color.valueOf(settings.getColor2()));
            positionText.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");

            Text companyText = new Text(workExperience[i].getCompany() + ", " + workExperience[i].getLocation());
            companyText.setFill(Color.valueOf(settings.getColor4()));
            companyText.setStyle("-fx-font-size: 12;");

            VBox objectivesListContainer = new VBox();
            objectivesListContainer.setPadding(new Insets(4, 0, 0, 0));

            Objective[] objectives = workExperience[i].getObjectives();

            for (int j = 0; j < objectives.length; j++) {
                HBox objectiveContainer = new HBox();
                objectiveContainer.setSpacing(8);

                Text bullet = new Text("•");
                bullet.setStyle("-fx-font-size: 11;");
                bullet.setFill(Color.valueOf(settings.getColor2()));

                Text objectiveText = new Text(objectives[j].getText());
                objectiveText.setStyle("-fx-font-size: 11;");
                objectiveText.setFill(Color.valueOf(settings.getColor2()));

                objectiveContainer.getChildren().addAll(bullet, autoWrap(objectiveText));

                objectivesListContainer.getChildren().add(objectiveContainer);
            }

            jobInfoContainer.getChildren().addAll(positionText, companyText, objectivesListContainer);

            workContainer.getChildren().addAll(startEndInfoContainer, jobInfoContainer);

            workExperienceListContainer.getChildren().add(workContainer);
        }

        workExperienceContainer.getChildren().addAll(workExperienceTextContainer, workExperienceListContainer);

        VBox educationContainer = new VBox();
        educationContainer.setPadding(new Insets(0, 12, 0, 12));

        VBox educationTextContainer = new VBox();
        educationTextContainer.setStyle("-fx-border-width: 0 0 1 0; -fx-border-color: " + settings.getColor6() + ";");
        educationTextContainer.setPadding(new Insets(0, 0, 4, 0));

        Text educationText = new Text("Education");
        educationText.setFill(Color.valueOf(settings.getColor3()));
        educationText.setStyle("-fx-font-size: 16; -fx-font-weight: bold");

        educationTextContainer.getChildren().addAll(educationText);

        VBox educationListContainer = new VBox();
        educationListContainer.setSpacing(8);
        educationListContainer.setPadding(new Insets(8, 0, 8, 0));

        Education[] education = resume.getEducation();

        for (int i = 0; i < education.length; i++) {
            HBox educationEntryContainer = new HBox();
            educationEntryContainer.setSpacing(16);

            VBox startEndInfoContainer = new VBox();
            startEndInfoContainer.setStyle("-fx-font-size: 12;");
            startEndInfoContainer.setMinWidth(85);

            Text startMonthText = new Text(education[i].getStart() + " to");
            startMonthText.setFill(Color.valueOf(settings.getColor2()));

            Text endMonthText = new Text(education[i].getEnd());
            endMonthText.setFill(Color.valueOf(settings.getColor2()));

            startEndInfoContainer.getChildren().addAll(startMonthText, endMonthText);

            VBox almaMaterContainer = new VBox();
            almaMaterContainer.setSpacing(4);

            Text credentialInstitutionText = new Text(education[i].getCredential() + ", " +
                    education[i].getInstitution() + ", " + education[i].getLocation());
            credentialInstitutionText.setFill(Color.valueOf(settings.getColor2()));
            credentialInstitutionText.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");

            VBox fieldOfStudyContainer = new VBox();
            fieldOfStudyContainer.setMaxWidth(Double.POSITIVE_INFINITY);
            HBox.setHgrow(fieldOfStudyContainer, Priority.ALWAYS);

            Text fieldOfStudyText = new Text("Field Of Study: " + education[i].getFieldOfStudy());
            fieldOfStudyText.setFill(Color.valueOf(settings.getColor4()));
            fieldOfStudyText.setStyle("-fx-font-size: 12;");

            fieldOfStudyContainer.getChildren().add(fieldOfStudyText);

            almaMaterContainer.getChildren().addAll(autoWrap(credentialInstitutionText), fieldOfStudyContainer);

            educationEntryContainer.getChildren().addAll(startEndInfoContainer, almaMaterContainer);

            educationListContainer.getChildren().add(educationEntryContainer);
        }

        educationContainer.getChildren().addAll(educationTextContainer, educationListContainer);

        rPanel.getChildren().addAll(summaryContainer, workExperienceContainer, educationContainer);

        resumeContainer.getChildren().addAll(lPanel, rPanel);

        return resumeContainer;
    }

    public static Node buildTemplate2(Resume resume, ResumeStyleSettings settings) {
        VBox resumeContainer = new VBox();
        String style = String.format("-fx-font-family: \"%s\"; -fx-background-color: %s",
                settings.getFont(), settings.getColor1());
        resumeContainer.setPrefHeight(RESUME_HEIGHT);
        resumeContainer.setPrefWidth((8.5 / 11.0) * RESUME_HEIGHT);
        resumeContainer.setMaxWidth((8.5 / 11.0) * RESUME_HEIGHT);
        resumeContainer.setStyle("fx-border-width: 1; -fx-border-color: #0004; " + style);
        resumeContainer.setPadding(new Insets(12, 12, 12, 12));

        HBox personalInfoContainer = new HBox();
        personalInfoContainer.setStyle("-fx-border-width: 0 0 2 0; -fx-border-color: " + settings.getColor5() + ";");
        personalInfoContainer.setPadding(new Insets(0, 0, 8, 0));
        personalInfoContainer.setSpacing(8);

        VBox initialsContainer = new VBox();
        initialsContainer.setStyle("-fx-background-color: " + settings.getColor3() + ";");
        initialsContainer.setAlignment(Pos.CENTER);
        initialsContainer.setPrefWidth(40);
        initialsContainer.setMaxHeight(40);

        String[] splitName = resume.getName().toUpperCase().split(" ");
        StringBuilder initials = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            if (i < splitName.length && !splitName[i].isEmpty())
                initials.append(splitName[i].charAt(0));
        }

        Text initialsText = new Text(initials.toString());
        initialsText.setFill(Color.valueOf(settings.getColor1()));
        initialsText.setStyle("-fx-font-size: 20;");

        initialsContainer.getChildren().add(initialsText);

        VBox nameRoleContainer = new VBox();
        nameRoleContainer.setPadding(new Insets(-4, 0, 0, 0));
        HBox.setHgrow(nameRoleContainer, Priority.ALWAYS);

        Text nameText = new Text(resume.getName().toUpperCase());
        nameText.setFill(Color.valueOf(settings.getColor3()));
        nameText.setStyle("-fx-font-size: 20;");

        Text roleText = new Text(resume.getProfession().toUpperCase());
        roleText.setFill(Color.valueOf(settings.getColor4()));
        roleText.setStyle("-fx-font-size: 14; -fx-font-weight: bold");

        nameRoleContainer.getChildren().addAll(nameText, roleText);

        VBox contactsContainer = new VBox();
        contactsContainer.setAlignment(Pos.CENTER_RIGHT);

        Text addressText = new Text(resume.getAddress());
        addressText.setStyle("-fx-font-size: 12;");
        addressText.setFill(Color.valueOf(settings.getColor2()));

        Text phoneText = new Text(resume.getPhone());
        phoneText.setStyle("-fx-font-size: 12;");
        phoneText.setFill(Color.valueOf(settings.getColor2()));

        Text emailText = new Text(resume.getEmail());
        emailText.setStyle("-fx-font-size: 12;");
        emailText.setFill(Color.valueOf(settings.getColor2()));

        contactsContainer.getChildren().addAll(addressText, phoneText, emailText);

        personalInfoContainer.getChildren().addAll(initialsContainer, nameRoleContainer, contactsContainer);

        HBox professionalSummaryContainer = new HBox();
        professionalSummaryContainer.setPadding(new Insets(8, 0, 8, 0));
        professionalSummaryContainer.setStyle("-fx-border-width: 0 0 2 0; -fx-border-color: " + settings.getColor5() + ";");
        professionalSummaryContainer.setSpacing(8);

        VBox professionalSummaryTextContainer = new VBox();
        professionalSummaryTextContainer.setMinWidth(120);

        Text professionalSummaryText = new Text("PROFESSIONAL SUMMARY");
        professionalSummaryText.setStyle("-fx-font-weight: bold; -fx-font-size: 13;");
        professionalSummaryText.setFill(Color.valueOf(settings.getColor2()));

        professionalSummaryTextContainer.getChildren().add(autoWrap(professionalSummaryText));

        Text professionalSummary = new Text(resume.getSummary());
        professionalSummary.setStyle("-fx-font-size: 11;");
        professionalSummary.setFill(Color.valueOf(settings.getColor2()));

        professionalSummaryContainer.getChildren().addAll(professionalSummaryTextContainer,
                autoWrap(professionalSummary));

        HBox skillsContainer = new HBox();
        skillsContainer.setPadding(new Insets(8, 0, 8, 0));
        skillsContainer.setStyle("-fx-border-width: 0 0 2 0; -fx-border-color: " + settings.getColor5() + ";");
        skillsContainer.setSpacing(8);

        VBox skillsTextContainer = new VBox();
        skillsTextContainer.setMinWidth(120);

        Text skillsText = new Text("SKILLS");
        skillsText.setStyle("-fx-font-weight: bold; -fx-font-size: 13;");
        skillsText.setFill(Color.valueOf(settings.getColor2()));

        skillsTextContainer.getChildren().add(skillsText);

        VBox skillsListContainer = new VBox();
        skillsListContainer.setMaxWidth(Double.POSITIVE_INFINITY);
        skillsListContainer.setStyle("-fx-font-size: 12");
        HBox.setHgrow(skillsListContainer, Priority.ALWAYS);

        int numRows = (int) Math.ceil(resume.getSkills().length / 3.0);

        for (int i = 0; i < numRows; i++) {
            HBox skillsRow = new HBox();
            skillsRow.setSpacing(8);

            for (int j = 0; j < 2; j++) {
                if (i * 2 + j < resume.getSkills().length) {
                    HBox skillContainer = new HBox();
                    skillContainer.setSpacing(8);
                    skillContainer.minWidthProperty().bind(skillsRow.widthProperty().subtract(8).divide(2));

                    Text bullet = new Text("•");
                    bullet.setFill(Color.valueOf(settings.getColor2()));

                    Text skill = new Text(resume.getSkills()[i * 2 + j]);
                    skill.setFill(Color.valueOf(settings.getColor2()));

                    skillContainer.getChildren().addAll(bullet, autoWrap(skill));

                    skillsRow.getChildren().add(skillContainer);
                }
            }

            skillsListContainer.getChildren().add(skillsRow);
        }

        skillsContainer.getChildren().addAll(skillsTextContainer, skillsListContainer);

        HBox technicalSkillsContainer = new HBox();
        technicalSkillsContainer.setPadding(new Insets(8, 0, 8, 0));
        technicalSkillsContainer.setStyle("-fx-border-width: 0 0 2 0; -fx-border-color: " + settings.getColor5() + ";");
        technicalSkillsContainer.setSpacing(8);

        VBox technicalSkillsTextContainer = new VBox();
        technicalSkillsTextContainer.setMinWidth(120);
        technicalSkillsTextContainer.setMaxWidth(120);

        Text technicalSkillsText = new Text("TECHNICAL SKILLS");
        technicalSkillsText.setStyle("-fx-font-weight: bold; -fx-font-size: 13;");
        technicalSkillsText.setFill(Color.valueOf(settings.getColor2()));

        technicalSkillsTextContainer.getChildren().add(autoWrap(technicalSkillsText));

        VBox technicalSkillsListContainer = new VBox();
        technicalSkillsListContainer.setMaxWidth(Double.POSITIVE_INFINITY);
        technicalSkillsListContainer.setStyle("-fx-font-size: 12");
        HBox.setHgrow(technicalSkillsListContainer, Priority.ALWAYS);

        numRows = (int) Math.ceil(resume.getTechnicalSkills().length / 3.0);

        for (int i = 0; i < numRows; i++) {
            HBox skillsRow = new HBox();
            skillsRow.setSpacing(8);

            for (int j = 0; j < 2; j++) {
                if (i * 2 + j < resume.getTechnicalSkills().length) {
                    HBox skillContainer = new HBox();
                    skillContainer.setSpacing(8);
                    skillContainer.minWidthProperty().bind(skillsRow.widthProperty().subtract(8).divide(2));

                    Text bullet = new Text("•");
                    bullet.setFill(Color.valueOf(settings.getColor2()));

                    Text skill = new Text(resume.getTechnicalSkills()[i * 2 + j]);
                    skill.setFill(Color.valueOf(settings.getColor2()));

                    skillContainer.getChildren().addAll(bullet, autoWrap(skill));

                    skillsRow.getChildren().add(skillContainer);
                }
            }

            technicalSkillsListContainer.getChildren().add(skillsRow);
        }

        technicalSkillsContainer.getChildren().addAll(technicalSkillsTextContainer, technicalSkillsListContainer);

        HBox workExperienceContainer = new HBox();
        workExperienceContainer.setPadding(new Insets(8, 0, 8, 0));
        workExperienceContainer.setStyle("-fx-border-width: 0 0 2 0; -fx-border-color: " + settings.getColor5() + ";");
        workExperienceContainer.setSpacing(8);

        VBox workExperienceTextContainer = new VBox();
        workExperienceTextContainer.setMinWidth(120);

        Text experienceText = new Text("EXPERIENCE");
        experienceText.setStyle("-fx-font-weight: bold; -fx-font-size: 13;");
        experienceText.setFill(Color.valueOf(settings.getColor2()));

        workExperienceTextContainer.getChildren().add(experienceText);

        VBox workExperienceListContainer = new VBox();
        workExperienceListContainer.setSpacing(8);

        Work[] work = resume.getWorkExperience();

        for (int i = 0; i < work.length; i++) {
            VBox workContainer = new VBox();
            workContainer.setSpacing(2);

            VBox companyInfoContainer = new VBox();

            Text startEndMonthsText = new Text(work[i].getStart() + " - " + work[i].getEnd());
            startEndMonthsText.setStyle("-fx-font-size: 11;");
            startEndMonthsText.setFill(Color.valueOf(settings.getColor4()));

            Text positionText = new Text(work[i].getPosition());
            positionText.setStyle("-fx-font-size: 13");
            positionText.setFill(Color.valueOf(settings.getColor2()));

            Text companyText = new Text(work[i].getCompany() + " | " + work[i].getLocation());
            companyText.setStyle("-fx-font-size: 12");
            companyText.setFill(Color.valueOf(settings.getColor2()));

            companyInfoContainer.getChildren().addAll(startEndMonthsText, positionText, companyText);

            VBox objectiveListContainer = new VBox();
            Objective[] objectives = work[i].getObjectives();

            for (int j = 0; j < objectives.length; j++) {
                HBox objectiveContainer = new HBox();
                objectiveContainer.setSpacing(8);
                objectiveContainer.setStyle("-fx-font-size: 11;");

                Text bullet = new Text("•");
                bullet.setFill(Color.valueOf(settings.getColor2()));

                Text objectiveText = new Text(objectives[j].getText());
                objectiveText.setFill(Color.valueOf(settings.getColor2()));

                objectiveContainer.getChildren().addAll(bullet, autoWrap(objectiveText));

                objectiveListContainer.getChildren().add(objectiveContainer);
            }

            workContainer.getChildren().addAll(companyInfoContainer, objectiveListContainer);

            workExperienceListContainer.getChildren().add(workContainer);
        }

        workExperienceContainer.getChildren().addAll(workExperienceTextContainer, workExperienceListContainer);

        HBox educationHistoryContainer = new HBox();
        educationHistoryContainer.setPadding(new Insets(8, 0, 0, 0));
        educationHistoryContainer.setSpacing(8);

        VBox educationTextContainer = new VBox();
        educationTextContainer.setMinWidth(120);

        Text educationText = new Text("EDUCATION");
        educationText.setStyle("-fx-font-weight: bold; -fx-font-size: 13;");
        educationText.setFill(Color.valueOf(settings.getColor2()));

        educationTextContainer.getChildren().add(educationText);

        VBox educationListContainer = new VBox();
        educationListContainer.setSpacing(8);

        Education[] education = resume.getEducation();

        for (int i = 0; i < education.length; i++) {
            HBox educationContainer = new HBox();
            educationContainer.setStyle("-fx-font-size: 13;");
            educationContainer.setSpacing(8);

            VBox startEndContainer = new VBox();
            startEndContainer.setMinWidth(105);
            startEndContainer.setMaxWidth(105);

            Text startEndMonthText = new Text(education[i].getStart() + " - " + education[i].getEnd());
            startEndMonthText.setStyle("-fx-font-size: 13;");
            startEndMonthText.setFill(Color.valueOf(settings.getColor4()));

            startEndContainer.getChildren().add(autoWrap(startEndMonthText));

            VBox credentialFieldOfStudyInstitutionContainer = new VBox();

            Text credentialFieldOfStudyText = new Text(education[i].getCredential() + " - " + education[i].getFieldOfStudy());
            credentialFieldOfStudyText.setFill(Color.valueOf(settings.getColor2()));

            Text institutionText = new Text(education[i].getInstitution() + ", " + education[i].getLocation());
            institutionText.setFill(Color.valueOf(settings.getColor2()));

            credentialFieldOfStudyInstitutionContainer.getChildren().addAll(autoWrap(credentialFieldOfStudyText),
                    autoWrap(institutionText));

            educationContainer.getChildren().addAll(startEndContainer, credentialFieldOfStudyInstitutionContainer);

            educationListContainer.getChildren().add(educationContainer);
        }

        educationHistoryContainer.getChildren().addAll(educationTextContainer, educationListContainer);

        resumeContainer.getChildren().addAll(personalInfoContainer, professionalSummaryContainer,
                skillsContainer, technicalSkillsContainer, workExperienceContainer, educationHistoryContainer);

        return resumeContainer;
    }

    public static Node buildTemplate3(Resume resume, ResumeStyleSettings settings) {
        HBox resumeContainer = new HBox();
        String fontFamilyStyle = String.format("-fx-font-family: \"%s\"; -fx-background-color: %s;",
                settings.getFont(), settings.getColor1());
        resumeContainer.setPrefHeight(RESUME_HEIGHT);
        resumeContainer.setPrefWidth((8.5 / 11.0) * RESUME_HEIGHT);
        resumeContainer.setMaxWidth((8.5 / 11.0) * RESUME_HEIGHT);
        resumeContainer.setStyle("fx-border-width: 1; -fx-border-color: #0004; " + fontFamilyStyle);

        VBox lPane = new VBox();
        lPane.setMinWidth(185);
        lPane.setMaxWidth(185);
        lPane.setSpacing(16);
        lPane.setStyle("-fx-border-width: 0 1 0 0; -fx-border-color: " + settings.getColor6() + ";");
        lPane.setPadding(new Insets(12, 12, 12, 12));

        VBox pictureContainer = new VBox();
        pictureContainer.setFillWidth(false);
        pictureContainer.setAlignment(Pos.CENTER);

        if (resume.getAvatar() != null) {
            VBox frame = new VBox();
            frame.setStyle("-fx-border-width: 2; -fx-border-radius: 100; -fx-border-color: " + settings.getColor3() + ";");
            frame.setPadding(new Insets(4, 4, 4, 4));
            frame.getChildren().add(Util.createAvatar(resume.getAvatar(), 100));
            pictureContainer.getChildren().add(frame);
        }

        VBox skillsContainer = new VBox();
        skillsContainer.setSpacing(8);

        Text skillsText = new Text("SKILLS");
        skillsText.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
        skillsText.setFill(Color.valueOf(settings.getColor3()));

        VBox skillsListContainer = new VBox();
        skillsListContainer.setSpacing(4);

        for (int i = 0; i < resume.getSkills().length; i++) {
            VBox skillContainer = new VBox();
            skillContainer.setStyle("-fx-background-radius: 5; -fx-background-color: " + settings.getColor5() + ";" );
            skillContainer.setPadding(new Insets(6, 8, 6, 8));
            skillContainer.setMaxWidth(Double.NEGATIVE_INFINITY);

            Text skillText = new Text(resume.getSkills()[i]);
            skillText.setStyle("-fx-font-size: 12;");
            skillText.setFill(Color.WHITE);

            skillContainer.getChildren().addAll(autoWrap(skillText));
            skillsListContainer.getChildren().add(skillContainer);
        }

        skillsContainer.getChildren().addAll(skillsText, skillsListContainer);

        VBox technicalSkillsContainer = new VBox();
        technicalSkillsContainer.setSpacing(8);

        Text techincalSkillsText = new Text("TECHNICAL SKILLS");
        techincalSkillsText.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
        techincalSkillsText.setFill(Color.valueOf(settings.getColor3()));

        VBox technicalSkillsListContainer = new VBox();
        technicalSkillsListContainer.setSpacing(4);

        for (int i = 0; i < resume.getTechnicalSkills().length; i++) {
            VBox skillContainer = new VBox();
            skillContainer.setStyle("-fx-background-radius: 5; -fx-background-color: " + settings.getColor5() + ";" );
            skillContainer.setPadding(new Insets(6, 8, 6, 8));
            skillContainer.setMaxWidth(Double.NEGATIVE_INFINITY);

            Text skillText = new Text(resume.getTechnicalSkills()[i]);
            skillText.setStyle("-fx-font-size: 12;");
            skillText.setFill(Color.WHITE);

            skillContainer.getChildren().addAll(autoWrap(skillText));
            technicalSkillsListContainer.getChildren().add(skillContainer);
        }

        technicalSkillsContainer.getChildren().addAll(autoWrap(techincalSkillsText), technicalSkillsListContainer);

        lPane.getChildren().addAll(pictureContainer, skillsContainer, technicalSkillsContainer);

        VBox rPane = new VBox();
        rPane.setPadding(new Insets(8, 16, 16, 16));
        HBox.setHgrow(rPane, Priority.ALWAYS);

        VBox personalInfoContainer = new VBox();
        personalInfoContainer.setSpacing(8);
        personalInfoContainer.setPadding(new Insets(0, 0, 16, 0));

        VBox nameRoleContainer = new VBox();

        Text nameText = new Text(resume.getName());
        nameText.setStyle("-fx-font-size: 22");
        nameText.setFill(Color.valueOf(settings.getColor3()));

        Text roleText = new Text(resume.getProfession());
        roleText.setStyle("-fx-font-size: 16");
        roleText.setFill(Color.valueOf(settings.getColor4()));

        nameRoleContainer.getChildren().addAll(nameText, roleText);

        Text professionalSummaryText = new Text(resume.getSummary());
        professionalSummaryText.setStyle("-fx-font-size: 11");
        professionalSummaryText.setFill(Color.valueOf(settings.getColor2()));

        HBox contactInfoContainer = new HBox();
        contactInfoContainer.setSpacing(8);

        VBox emailAddressContainer = new VBox();
        HBox.setHgrow(emailAddressContainer, Priority.ALWAYS);
        emailAddressContainer.maxWidthProperty().bind(contactInfoContainer.widthProperty().divide(2));

        HBox emailContainer = new HBox();
        emailContainer.setSpacing(4);

        FontIcon emailIcon = new FontIcon();
        emailIcon.setIconLiteral("gmi-email");
        emailIcon.setFill(Color.valueOf(settings.getColor4()));
        emailIcon.getTransforms().add(new Translate(0, 1));

        Text emailText = new Text(resume.getEmail());
        emailText.setStyle("-fx-font-size: 12");
        emailText.setFill(Color.valueOf(settings.getColor2()));

        emailContainer.getChildren().addAll(emailIcon, autoWrap(emailText));

        HBox addressContainer = new HBox();
        addressContainer.setSpacing(4);

        FontIcon locationIcon = new FontIcon();
        locationIcon.setIconLiteral("gmi-location-on");
        locationIcon.setFill(Color.valueOf(settings.getColor4()));
        locationIcon.getTransforms().add(new Translate(0, 1));

        Text addressText = new Text(resume.getAddress());
        addressText.setStyle("-fx-font-size: 12");
        addressText.setFill(Color.valueOf(settings.getColor2()));

        addressContainer.getChildren().addAll(locationIcon, autoWrap(addressText));

        emailAddressContainer.getChildren().addAll(emailContainer, addressContainer);

        HBox phoneContainer = new HBox();
        phoneContainer.setSpacing(4);
        phoneContainer.maxWidthProperty().bind(contactInfoContainer.widthProperty().divide(2));
        HBox.setHgrow(phoneContainer, Priority.ALWAYS);

        FontIcon phoneIcon = new FontIcon();
        phoneIcon.setIconLiteral("gmi-smartphone");
        phoneIcon.setFill(Color.valueOf(settings.getColor4()));
        phoneIcon.getTransforms().add(new Translate(0, 1));

        Text phoneText = new Text(resume.getPhone());
        phoneText.setStyle("-fx-font-size: 12");
        phoneText.setFill(Color.valueOf(settings.getColor2()));

        phoneContainer.getChildren().addAll(phoneIcon, autoWrap(phoneText));

        contactInfoContainer.getChildren().addAll(emailAddressContainer, phoneContainer);

        personalInfoContainer.getChildren().addAll(nameRoleContainer, autoWrap(professionalSummaryText),
                contactInfoContainer);

        VBox workExperienceContainer = new VBox();
        workExperienceContainer.setSpacing(8);
        workExperienceContainer.setPadding(new Insets(0, 0, 16, 0));

        Text workExperienceText = new Text("WORK EXPERIENCE");
        workExperienceText.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
        workExperienceText.setFill(Color.valueOf(settings.getColor3()));

        VBox workExperienceListContainer = new VBox();
        workExperienceListContainer.setSpacing(8);

        Work[] workExperience = resume.getWorkExperience();

        for (int i = 0; i < workExperience.length; i++) {
            VBox workContainer = new VBox();
            workContainer.setSpacing(4);

            VBox jobInfoOverviewContainer = new VBox();

            Text companyNameText = new Text(workExperience[i].getCompany());
            companyNameText.setStyle("-fx-font-size: 13; -fx-font-weight: bold;");
            companyNameText.setFill(Color.valueOf(settings.getColor2()));

            Text positionText = new Text(workExperience[i].getPosition());
            positionText.setStyle("-fx-font-size: 13;");
            positionText.setFill(Color.valueOf(settings.getColor2()));

            HBox startEndLocationContainer = new HBox();

            VBox startEndContainer = new VBox();
            HBox.setHgrow(startEndContainer, Priority.ALWAYS);

            Text startEndMonthText = new Text(workExperience[i].getStart() + " - " + workExperience[i].getEnd());
            startEndMonthText.setStyle("-fx-font-size: 11; -fx-font-style: italic;");
            startEndMonthText.setFill(Color.valueOf(settings.getColor4()));

            startEndContainer.getChildren().addAll(startEndMonthText);

            Text locationText = new Text(workExperience[i].getLocation());
            locationText.setStyle("-fx-font-size: 11; -fx-font-style: italic;");
            locationText.setFill(Color.valueOf(settings.getColor4()));

            startEndLocationContainer.getChildren().addAll(startEndContainer, locationText);

            jobInfoOverviewContainer.getChildren().addAll(companyNameText, positionText, startEndLocationContainer);

            VBox objectivesContainer = new VBox();

            Text achievementsTasksText = new Text("Achievement/Tasks");
            achievementsTasksText.setFill(Color.valueOf(settings.getColor3()));
            achievementsTasksText.setStyle("-fx-font-size: 10; -fx-font-style: italic;");

            VBox objectivesListContainer = new VBox();

            for (int j = 0; j < workExperience[i].getObjectives().length; j++) {
                HBox objectiveContainer = new HBox();
                objectiveContainer.setSpacing(4);

                Text bullet = new Text("◦");
                bullet.setStyle("-fx-font-size: 11;");
                bullet.setFill(Color.valueOf(settings.getColor2()));

                Text objectiveText = new Text(workExperience[i].getObjectives()[j].getText());
                objectiveText.setStyle("-fx-font-size: 11;");
                objectiveText.setFill(Color.valueOf(settings.getColor2()));

                objectiveContainer.getChildren().addAll(bullet, autoWrap(objectiveText));

                objectivesListContainer.getChildren().add(objectiveContainer);
            }

            if (workExperience[i].getObjectives().length > 0)
                objectivesContainer.getChildren().add(achievementsTasksText);

            objectivesContainer.getChildren().addAll(objectivesListContainer);

            workContainer.getChildren().addAll(jobInfoOverviewContainer, objectivesContainer);

            workExperienceListContainer.getChildren().add(workContainer);
        }

        workExperienceContainer.getChildren().addAll(workExperienceText, workExperienceListContainer);

        VBox educationContainer = new VBox();
        educationContainer.setSpacing(8);

        Text educationText = new Text("EDUCATION");
        educationText.setStyle("-fx-font-size: 15; -fx-font-weight: bold");
        educationText.setFill(Color.valueOf(settings.getColor3()));

        VBox educationListContainer = new VBox();
        educationListContainer.setSpacing(6);

        Education[] education = resume.getEducation();

        for (int i = 0; i < education.length; i++) {
            VBox educationEntryContainer = new VBox();

            Text credentialFieldOfStudyText = new Text(education[i].getCredential() + " in " + education[i].getFieldOfStudy());
            credentialFieldOfStudyText.setStyle("-fx-font-size: 13; -fx-font-weight: bold;");
            credentialFieldOfStudyText.setFill(Color.valueOf(settings.getColor2()));

            Text institutionNameText = new Text(education[i].getInstitution());
            institutionNameText.setStyle("-fx-font-size: 13;");
            institutionNameText.setFill(Color.valueOf(settings.getColor2()));

            HBox startEndLocationContainer = new HBox();

            VBox startEndContainer = new VBox();
            HBox.setHgrow(startEndContainer, Priority.ALWAYS);

            Text startEndMonthText = new Text(education[i].getStart() + " - " + education[i].getEnd());
            startEndMonthText.setStyle("-fx-font-size: 11; -fx-font-style: italic;");
            startEndMonthText.setFill(Color.valueOf(settings.getColor4()));

            startEndContainer.getChildren().addAll(startEndMonthText);

            Text locationText = new Text(education[i].getLocation());
            locationText.setStyle("-fx-font-size: 11; -fx-font-style: italic;");
            locationText.setFill(Color.valueOf(settings.getColor4()));

            startEndLocationContainer.getChildren().addAll(startEndContainer, locationText);

            educationEntryContainer.getChildren().addAll(autoWrap(credentialFieldOfStudyText),
                    autoWrap(institutionNameText), startEndLocationContainer);

            educationListContainer.getChildren().addAll(educationEntryContainer);
        }

        educationContainer.getChildren().addAll(educationText, educationListContainer);

        rPane.getChildren().addAll(personalInfoContainer, workExperienceContainer, educationContainer);

        resumeContainer.getChildren().addAll(lPane, rPane);

        return resumeContainer;
    }

    private static TextFlow autoWrap(Text text) {
        TextFlow flow = new TextFlow();
        Node parent = text.getParent();

        if (parent instanceof Region)
            flow.prefWidthProperty().bind(((Region)parent).widthProperty());

        flow.getChildren().add(text);
        return flow;
    }
}
