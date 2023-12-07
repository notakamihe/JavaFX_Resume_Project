package com.example.resume_project.models;

public class Education {
    private int id;
    private String start;
    private String end;
    private String credential;
    private String fieldOfStudy;
    private String institution;
    private String location;

    public Education(String start, String end, String credential, String fieldOfStudy, String institution, String location) {
        this.start = start;
        this.end = end;
        this.credential = credential;
        this.fieldOfStudy = fieldOfStudy;
        this.institution = institution;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
