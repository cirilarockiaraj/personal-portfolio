package com.app.personal_portfolio.entity.components;

import java.util.List;

public class Education {
    private String course;
    private String durationYears;
    private String institute;
    private List<Object> description;
    
    public String getCourse() {
        return course;
    }
    public void setCourse(String course) {
        this.course = course;
    }
    public String getDurationYears() {
        return durationYears;
    }
    public void setDurationYears(String durationYears) {
        this.durationYears = durationYears;
    }
    public String getInstitute() {
        return institute;
    }
    public void setInstitute(String institute) {
        this.institute = institute;
    }
    public List<Object> getDescription() {
        return description;
    }
    public void setDescription(List<Object> description) {
        this.description = description;
    }
    
}
