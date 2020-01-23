package com.javaops.web.model;

import java.util.List;

/**
 * @author Vasichkin Pavel
 */
public class ListSection implements Section {
    private final List<String> skills;

    public ListSection(List<String> skills) {
        this.skills = skills;
    }

    public List<String> getSkills() {
        return skills;
    }

    @Override
    public String toString() {
        return "ListSection{" + "skills=" + skills + '}';
    }
}
