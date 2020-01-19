package com.javaops.web.model;

import java.util.List;

/**
 * @author Vasichkin Pavel
 */
public class ListSection implements Section {
    private List<String> skills;

    public ListSection(List<String> skills) {
        this.skills = skills;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
