package com.app.personal_portfolio.entity;

import java.util.List;
import java.util.Map;

import com.app.personal_portfolio.entity.components.Education;
import com.app.personal_portfolio.entity.components.Experience;

public class Resume {
    private Map<String, String> summary;
    private List<Education> education;
    private List<Experience> experience;
    
    public Map<String, String> getSummary() {
        return summary;
    }
    public void setSummary(Map<String, String> summary) {
        this.summary = summary;
    }
    public List<Education> getEducation() {
        return education;
    }
    public void setEducation(List<Education> education) {
        this.education = education;
    }
    public List<Experience> getExperience() {
        return experience;
    }
    public void setExperience(List<Experience> experience) {
        this.experience = experience;
    }
    
}
