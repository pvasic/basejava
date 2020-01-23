package com.javaops.web.model;

/**
 * @author Vasichkin Pavel
 */
public class TextSection implements Section {
    private final String description;

    public TextSection(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }



    @Override
    public String toString() {
        return "TextSection{" + "description='" + description + '\'' + '}';
    }
}
