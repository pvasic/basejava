package com.javaops.web.model;

import java.util.List;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ListSection that = (ListSection) o;
        return skills.equals(that.skills);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skills);
    }

    @Override
    public String toString() {
        return "ListSection{" + "skills=" + skills + '}';
    }
}
