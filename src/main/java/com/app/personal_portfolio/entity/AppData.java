package com.app.personal_portfolio.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "AppData")
@Data
public class AppData {
    @Id
    private String id;
    private NavBar navBar;
    private Home home;
    private About about;
    private Resume resume;
    private Contact contact;
    
    public NavBar getNavBar() {
        return navBar;
    }
    public void setNavBar(NavBar navBar) {
        this.navBar = navBar;
    }
    public Home getHome() {
        return home;
    }
    public void setHome(Home home) {
        this.home = home;
    }
    public About getAbout() {
        return about;
    }
    public void setAbout(About about) {
        this.about = about;
    }
    public Resume getResume() {
        return resume;
    }
    public void setResume(Resume resume) {
        this.resume = resume;
    }
    public Contact getContact() {
        return contact;
    }
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    
}
