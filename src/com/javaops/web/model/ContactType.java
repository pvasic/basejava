package com.javaops.web.model;

/**
 * @author Vasichkin Pavel
 */
public enum ContactType {
    TEL("Телефон"),
    SKYPE("Skype"),
    EMAIL("Email"),
    LINKEDLN("LinkedIn"),
    GITHUB("GitHub"),
    STACKOVERFLOW("Stackoverflow");

    private String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
