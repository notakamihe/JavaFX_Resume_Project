package com.example.resume_project.app_utils;

import com.example.resume_project.models.*;
import javafx.scene.image.Image;

import java.sql.*;
import java.util.*;
import java.util.stream.Stream;

public class DB {
    private static final String JDBC_URL = "jdbc:mysql://localhost/resume?serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public static Connection connection;

    public static void setConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<ResumeSQL> getResumes() {
        List<ResumeSQL> resumes = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM resume;");

            while (set.next())
                resumes.add(ResumeSQL.fromResultSet(set, connection));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resumes;
    }

    public static void createResume(Resume resume, ResumeStyleSettings settings, Image snapshot) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO resume (title, name, profession, address, email, phone, summary, skills, " +
                "technical_skills, avatar, template, snapshot) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        );

        statement.setString(1, resume.getTitle());
        statement.setString(2, resume.getName());
        statement.setString(3, resume.getProfession());
        statement.setString(4, resume.getAddress());
        statement.setString(5, resume.getEmail());
        statement.setString(6, resume.getPhone());
        statement.setString(7, resume.getSummary());
        statement.setString(8, String.join("\n", resume.getSkills()));
        statement.setString(9, String.join("\n", resume.getTechnicalSkills()));
        statement.setBlob(10, Util.imageToStream(resume.getAvatar()));
        statement.setInt(11, AppStateSingleton.getInstance().getTemplate());
        statement.setBlob(12, Util.imageToStream(snapshot));
        statement.execute();

        ResultSet set = statement.getGeneratedKeys();

        if (set.next()) {
            resume.setId(set.getInt(1));

            for (int i = 0; i < resume.getWorkExperience().length; i++)
                createWork(resume.getWorkExperience()[i], resume.getId());

            for (int i = 0; i < resume.getEducation().length; i++)
                createEducation(resume.getEducation()[i], resume.getId());

            createStyleSettings(settings, resume.getId());
        }
    }

    public static void updateResume(Resume resume, ResumeStyleSettings settings, Image snapshot) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE resume SET title = ?, name = ?, profession = ?, address = ?, email = ?, phone = ?, " +
                "summary = ?, skills = ?, technical_skills = ?, avatar = ?, template = ?, snapshot = ? " +
                "WHERE id = ?"
        );

        statement.setString(1, resume.getTitle());
        statement.setString(2, resume.getName());
        statement.setString(3, resume.getProfession());
        statement.setString(4, resume.getAddress());
        statement.setString(5, resume.getEmail());
        statement.setString(6, resume.getPhone());
        statement.setString(7, resume.getSummary());
        statement.setString(8, String.join("\n", resume.getSkills()));
        statement.setString(9, String.join("\n", resume.getTechnicalSkills()));
        statement.setBlob(10, Util.imageToStream(resume.getAvatar()));
        statement.setInt(11, AppStateSingleton.getInstance().getTemplate());
        statement.setBlob(12, Util.imageToStream(snapshot));
        statement.setInt(13, resume.getId());
        statement.executeUpdate();

        ResultSet set = statement.executeQuery("SELECT * FROM work WHERE resume_id = " + resume.getId());

        while (set.next()) {
            if (!contains(Arrays.stream(resume.getWorkExperience()).map(Work::getId), set.getInt("id"))) {
                statement = connection.prepareStatement("DELETE FROM work WHERE id = ?");
                statement.setInt(1, set.getInt("id"));
                statement.execute();
            }
        }

        for (int i = 0; i < resume.getWorkExperience().length; i++) {
            if (resume.getWorkExperience()[i].getId() > 0)
                updateWork(resume.getWorkExperience()[i]);
            else
                createWork(resume.getWorkExperience()[i], resume.getId());
        }

        set = statement.executeQuery("SELECT * FROM education WHERE resume_id = " + resume.getId());

        while (set.next()) {
            if (!contains(Arrays.stream(resume.getEducation()).map(Education::getId), set.getInt("id"))) {
                statement = connection.prepareStatement("DELETE FROM education WHERE id = ?");
                statement.setInt(1, set.getInt("id"));
                statement.execute();
            }
        }

        for (int i = 0; i < resume.getEducation().length; i++) {
            if (resume.getEducation()[i].getId() > 0)
                updateEducation(resume.getEducation()[i]);
            else
                createEducation(resume.getEducation()[i], resume.getId());
        }

        set = statement.executeQuery("SELECT * FROM style_settings WHERE resume_id = " + resume.getId());

        if (set.next()) {
            settings.setId(set.getInt("id"));
            updateStyleSettings(settings);
        }
    }

    public static void deleteResume(Resume resume) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM resume WHERE id = ?");
            statement.setInt(1, resume.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createWork(Work work, int resumeId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO work (resume_id, start, end, company, position, location) " +
                " VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
        );

        statement.setInt(1, resumeId);
        statement.setString(2, work.getStart());
        statement.setString(3, work.getEnd());
        statement.setString(4, work.getCompany());
        statement.setString(5, work.getPosition());
        statement.setString(6, work.getLocation());
        statement.execute();

        ResultSet workResultSet = statement.getGeneratedKeys();
        if (workResultSet.next())
            work.setId(workResultSet.getInt(1));

        if (work.getId() > 0) {
            for (int j = 0; j < work.getObjectives().length; j++) {
                createObjective(work.getObjectives()[j], work.getId());
            }
        }
    }

    public static void updateWork(Work work) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE work SET start = ?, end = ?, company = ?, position = ?, location = ? WHERE id = ?"
        );

        statement.setString(1, work.getStart());
        statement.setString(2, work.getEnd());
        statement.setString(3, work.getCompany());
        statement.setString(4, work.getPosition());
        statement.setString(5, work.getLocation());
        statement.setInt(6, work.getId());
        statement.executeUpdate();

        ResultSet set = statement.executeQuery("SELECT * FROM objective WHERE work_id = " + work.getId());

        while (set.next()) {
            if (!contains(Arrays.stream(work.getObjectives()).map(Objective::getId), set.getInt("id"))) {
                statement = connection.prepareStatement("DELETE FROM objective WHERE id = ?");
                statement.setInt(1, set.getInt("id"));
                statement.execute();
            }
        }

        for (int j = 0; j < work.getObjectives().length; j++) {
            if (work.getObjectives()[j].getId() > 0)
                updateObjective(work.getObjectives()[j]);
            else
                createObjective(work.getObjectives()[j], work.getId());
        }
    }

    public static void createObjective(Objective objective, int workId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO objective (work_id, text) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS
        );

        statement.setInt(1, workId);
        statement.setString(2, objective.getText());
        statement.execute();

        ResultSet set = statement.getGeneratedKeys();

        if (set.next())
            objective.setId(set.getInt(1));
    }

    public static void updateObjective(Objective objective) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("UPDATE objective SET text = ? WHERE id = ?");
        statement.setString(1, objective.getText());
        statement.setInt(2, objective.getId());
        statement.executeUpdate();
    }

    public static void createEducation(Education education, int resumeId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO education (resume_id, start, end, credential, field_of_study, institution, " +
                "location) VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
        );

        statement.setInt(1, resumeId);
        statement.setString(2, education.getStart());
        statement.setString(3, education.getEnd());
        statement.setString(4, education.getCredential());
        statement.setString(5, education.getFieldOfStudy());
        statement.setString(6, education.getInstitution());
        statement.setString(7, education.getLocation());
        statement.execute();

        ResultSet educationResultSet = statement.getGeneratedKeys();

        if (educationResultSet.next())
            education.setId(educationResultSet.getInt(1));
    }

    public static void updateEducation(Education education) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE education SET start = ?, end = ?, credential = ?, field_of_study = ?, "  +
                "institution = ?, location = ? WHERE id = ?"
        );

        statement.setString(1, education.getStart());
        statement.setString(2, education.getEnd());
        statement.setString(3, education.getCredential());
        statement.setString(4, education.getFieldOfStudy());
        statement.setString(5, education.getInstitution());
        statement.setString(6, education.getLocation());
        statement.setInt(7, education.getId());
        statement.executeUpdate();
    }

    public static void createStyleSettings(ResumeStyleSettings settings, int resumeId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO style_settings (resume_id, font, color1, color2, color3, color4, color5, " +
                "color6) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
        );

        statement.setInt(1, resumeId);
        statement.setString(2, settings.getFont());
        statement.setString(3, settings.getColor1());
        statement.setString(4, settings.getColor2());
        statement.setString(5, settings.getColor3());
        statement.setString(6, settings.getColor4());
        statement.setString(7, settings.getColor5());
        statement.setString(8, settings.getColor6());
        statement.execute();

        ResultSet set = statement.getGeneratedKeys();

        if (set.next())
            settings.setId(set.getInt(1));
    }

    public static void updateStyleSettings(ResumeStyleSettings settings) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE style_settings SET font = ?, color1 = ?, color2 = ?, color3 = ?, color4 = ?, " +
                "color5 = ?, color6 = ? WHERE id = ?"
        );

        statement.setString(1, settings.getFont());
        statement.setString(2, settings.getColor1());
        statement.setString(3, settings.getColor2());
        statement.setString(4, settings.getColor3());
        statement.setString(5, settings.getColor4());
        statement.setString(6, settings.getColor5());
        statement.setString(7, settings.getColor6());
        statement.setInt(8, settings.getId());
        statement.executeUpdate();
    }

    public static <T extends Object> boolean contains(Stream<T> arr, T value) {
        return arr.filter(item -> arr.equals(value)).count() > 0;
    }
}
