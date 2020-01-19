package com.javaops.web.model;

/**
 * @author Vasichkin Pavel
 */
public class TextSection implements Section {
    private String description;

    public TextSection(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
