package com.example.resume_project.models;

import javafx.scene.image.Image;

public class Resume {
    private int id;
    private String title;
    private String name;
    private String profession;
    private String address;
    private String phone;
    private String email;
    private String summary;
    private Work[] workExperience;
    private Education[] education;
    private String[] skills;
    private String[] technicalSkills;
    private Image avatar;

    public Resume(String name, String profession, String address, String phone, String email, String summary,
                  Work[] workExperience, Education[] education, String[] skills, String[] technicalSkills) {
        this.name = name;
        this.profession = profession;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.summary = summary;
        this.workExperience = workExperience;
        this.education = education;
        this.skills = skills;
        this.technicalSkills = technicalSkills;
    }

    public Resume(String name, String position, String address, String phone, String email, String summary,
                  Work[] workExperience, Education[] education, String[] skills, String[] technicalSkills, Image avatar) {
        this(name, position, address, phone, email, summary, workExperience, education, skills, technicalSkills);
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Work[] getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(Work[] workExperience) {
        this.workExperience = workExperience;
    }

    public Education[] getEducation() {
        return education;
    }

    public void setEducation(Education[] education) {
        this.education = education;
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    public String[] getTechnicalSkills() {
        return technicalSkills;
    }

    public void setTechnicalSkills(String[] technicalSkills) {
        this.technicalSkills = technicalSkills;
    }

    @Override
    public String toString() {
        String str = "";

        System.out.printf("id = %d, name = %s, profession = %s, address = %s, email = %s, phone = %s,\n",
                id, name, profession, address, email, phone);
        System.out.println("summary = " + summary + ",");

        System.out.println("education = [");

        for (Education e : education) {
            System.out.printf("\tid = %s, start = %s, end = %s, credential = %s, fieldOfStudy = %s, institution = %s, location = %s,\n",
                    e.getId(), e.getStart(), e.getEnd(), e.getCredential(), e.getFieldOfStudy(), e.getInstitution(), e.getLocation());
        }

        System.out.println("],");

        System.out.println("workExperience = [");

        for (Work w : workExperience) {
            System.out.printf("\tid = %d, start = %s, end = %s, position = %s, company = %s, location = %s,\n",
                    w.getId(), w.getStart(), w.getEnd(), w.getPosition(), w.getCompany(), w.getLocation());

            System.out.println("\tobjectives = [");

            for (Objective objective : w.getObjectives())
                System.out.printf("\t\tid = %d, text = %s\n,", objective.getId(), objective.getText());

            System.out.println("\t]");
        }

        System.out.println("],");

        System.out.printf("skills = %s,\n", String.join(",", skills));
        System.out.printf("technicalSkills = %s", String.join(",", technicalSkills));

        return str;
    }

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }
}
