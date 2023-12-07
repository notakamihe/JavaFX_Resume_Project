package com.example.resume_project.app_utils;

import com.example.resume_project.models.Resume;
import com.example.resume_project.models.ResumeStyleSettings;

public class AppStateSingleton {
    private final static AppStateSingleton INSTANCE = new AppStateSingleton();

    private int template;
    private Resume resume;
    private ResumeStyleSettings resumeStyleSettings;
    private ResumeStyleSettings defaultStyleSettings;

    private AppStateSingleton() {
        this.template = -1;
    }

    public static AppStateSingleton getInstance() {
        return INSTANCE;
    }

    public int getTemplate() {
        return template;
    }

    public void setTemplate(int template) {
        this.template = template;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public ResumeStyleSettings getResumeStyleSettings() {
        return resumeStyleSettings;
    }

    public void setResumeStyleSettings(ResumeStyleSettings resumeStyleSettings) {
        this.resumeStyleSettings = resumeStyleSettings;
    }

    public ResumeStyleSettings getDefaultStyleSettings() {
        return defaultStyleSettings;
    }

    public void setDefaultStyleSettings(ResumeStyleSettings defaultStyleSettings) {
        this.defaultStyleSettings = defaultStyleSettings;
    }
}
