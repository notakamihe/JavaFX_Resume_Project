package com.example.resume_project.models;

public class ResumeStyleSettings {
    private int id;
    private String font;
    private String color1;
    private String color2;
    private String color3;
    private String color4;
    private String color5;
    private String color6;

    public ResumeStyleSettings(String font, String[] colors) {
        this.font = font;

        for (int i = 0; i < colors.length; i++) {
            switch (i) {
                case 0:
                    this.color1 = colors[i];
                    break;
                case 1:
                    this.color2 = colors[i];
                    break;
                case 2:
                    this.color3 = colors[i];
                    break;
                case 3:
                    this.color4 = colors[i];
                    break;
                case 4:
                    this.color5 = colors[i];
                    break;
                case 5:
                    this.color6 = colors[i];
                    break;
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getColor1() {
        return color1;
    }

    public void setColor1(String color1) {
        this.color1 = color1;
    }

    public String getColor2() {
        return color2;
    }

    public void setColor2(String color2) {
        this.color2 = color2;
    }

    public String getColor3() {
        return color3;
    }

    public void setColor3(String color3) {
        this.color3 = color3;
    }

    public String getColor4() {
        return color4;
    }

    public void setColor4(String color4) {
        this.color4 = color4;
    }

    public String getColor5() {
        return color5;
    }

    public void setColor5(String color5) {
        this.color5 = color5;
    }

    public String getColor6() {
        return color6;
    }

    public void setColor6(String color6) {
        this.color6 = color6;
    }
}
