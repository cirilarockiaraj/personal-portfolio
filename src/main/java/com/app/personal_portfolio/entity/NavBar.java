package com.app.personal_portfolio.entity;

import java.util.Map;

public class NavBar {
    private String profileImgUrl;
    private String name;
    private Map<String, String> socialMedia;
    
    public String getProfileImgUrl() {
        return profileImgUrl;
    }
    public void setProfileImgUrl(String profileImgUrl) {
        this.profileImgUrl = profileImgUrl;
    }
    public Map<String, String> getSocialMedia() {
        return socialMedia;
    }
    public void setSocialMedia(Map<String, String> socialMedia) {
        this.socialMedia = socialMedia;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    
}
