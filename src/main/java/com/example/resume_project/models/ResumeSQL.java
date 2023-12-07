package com.example.resume_project.models;

import com.example.resume_project.app_utils.Util;
import javafx.scene.image.Image;

import java.sql.*;
import java.util.*;

public class ResumeSQL extends Resume {
    private int template;
    private Image snapshot;
    private ResumeStyleSettings settings;

    public ResumeSQL(int id, String title, String name, String profession, String address, String phone, String email, String summary,
                     Work[] workExperience, Education[] education, String[] skills, String[] technicalSkills,
                     Image avatar, int template, Image snapshot, ResumeStyleSettings settings) {
        super(name, profession, address, phone, email, summary, workExperience, education, skills, technicalSkills, avatar);
        this.setId(id);
        this.setTitle(title);
        this.template = template;
        this.snapshot = snapshot;
        this.settings = settings;
    }

    public static ResumeSQL fromResultSet(ResultSet set, Connection connection) {
        ResumeSQL resume = null;

        try {
            int id = set.getInt("id");
            List<Work> workExperience = new ArrayList<>();
            List<Education> education = new ArrayList<>();

            Statement workStatement = connection.createStatement();
            ResultSet workSet = workStatement.executeQuery("SELECT * FROM work WHERE resume_id = " + id);

            while (workSet.next()) {
                int workId = workSet.getInt("id");

                String start = workSet.getString("start");
                String end = workSet.getString("end");
                String position = workSet.getString("position");
                String company = workSet.getString("company");
                String location = workSet.getString("location");
                List<Objective> objectives = new ArrayList<>();

                Statement objectiveStatement = connection.createStatement();
                ResultSet objectiveSet = objectiveStatement.executeQuery("SELECT * FROM objective WHERE work_id = " + workId);

                while (objectiveSet.next()) {
                    Objective objective = new Objective(objectiveSet.getString("text"));
                    objective.setId(objectiveSet.getInt("id"));
                    objectives.add(objective);
                }

                Work work = new Work(start, end, position, company, location, objectives.toArray(Objective[]::new));
                work.setId(workId);
                workExperience.add(work);
            }

            Statement educationStatement = connection.createStatement();
            ResultSet educationSet = educationStatement.executeQuery("SELECT * FROM education WHERE resume_id = " + id);

            while (educationSet.next()) {
                String start = educationSet.getString("start");
                String end = educationSet.getString("end");
                String credential = educationSet.getString("credential");
                String fieldOfStudy = educationSet.getString("field_of_study");
                String institution = educationSet.getString("institution");
                String location = educationSet.getString("location");

                Education ed = new Education(start, end, credential, fieldOfStudy, institution, location);
                ed.setId(educationSet.getInt("id"));
                education.add(ed);
            }

            ResumeStyleSettings settings = null;
            Statement settingsStatement = connection.createStatement();
            ResultSet settingsSet = settingsStatement.executeQuery("SELECT * FROM style_settings WHERE resume_id = " + id);

            if (settingsSet.next()) {
                settings = new ResumeStyleSettings(settingsSet.getString("font"), new String[] {
                        settingsSet.getString("color1"),
                        settingsSet.getString("color2"),
                        settingsSet.getString("color3"),
                        settingsSet.getString("color4"),
                        settingsSet.getString("color5"),
                        settingsSet.getString("color6"),
                });
            }

            String title = set.getString("title");
            String name = set.getString("name");
            String profession = set.getString("profession");
            String address = set.getString("address");
            String email = set.getString("email");
            String phone = set.getString("phone");
            String summary = set.getString("summary");
            String[] skills = set.getString("skills").split("\n");
            String[] technicalSkills = set.getString("technical_skills").split("\n");
            Image avatar = Util.streamToImage(set.getBinaryStream("avatar"));
            int template = set.getInt("template");
            Image snapshot = Util.streamToImage(set.getBinaryStream("snapshot"));

            resume = new ResumeSQL(id, title, name, profession, address, phone, email, summary,
                    workExperience.toArray(Work[]::new), education.toArray(Education[]::new),
                    skills, technicalSkills, avatar, template, snapshot, settings);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resume;
    }

    public int getTemplate() {
        return template;
    }

    public void setTemplate(int template) {
        this.template = template;
    }

    public Image getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(Image snapshot) {
        this.snapshot = snapshot;
    }

    public ResumeStyleSettings getSettings() {
        return settings;
    }

    public void setSettings(ResumeStyleSettings settings) {
        this.settings = settings;
    }
}
