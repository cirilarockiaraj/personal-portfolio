package com.app.personal_portfolio.entity.components;

import java.util.List;

public class Experience {
    private String companyName;
    private String durationYears;
    private String companyAddress;
    private List<Object> description;
    
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public String getDurationYears() {
        return durationYears;
    }
    public void setDurationYears(String durationYears) {
        this.durationYears = durationYears;
    }
    public String getCompanyAddress() {
        return companyAddress;
    }
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }
    public List<Object> getDescription() {
        return description;
    }
    public void setDescription(List<Object> description) {
        this.description = description;
    }
    
}
