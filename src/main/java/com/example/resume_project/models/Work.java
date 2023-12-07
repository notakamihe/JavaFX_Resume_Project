package com.example.resume_project.models;

public class Work {
    private int id;
    private String start;
    private String end;
    private String position;
    private String company;
    private String location;
    private Objective[] objectives;

    public Work(String start, String end, String position, String company, String location, Objective[] objectives) {
        this.start = start;
        this.end = end;
        this.position = position;
        this.company = company;
        this.location = location;
        this.objectives = objectives;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Objective[] getObjectives() {
        return objectives;
    }

    public void setObjectives(Objective[] objectives) {
        this.objectives = objectives;
    }
}
