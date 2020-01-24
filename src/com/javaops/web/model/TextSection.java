package com.javaops.web.model;

import java.util.Objects;

/**
 * @author Vasichkin Pavel
 */
public class TextSection implements Section {
    private final String description;

    public TextSection(String description) {
        Objects.requireNonNull(description, "description must not be null");
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        TextSection that = (TextSection) o;
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    @Override
    public String toString() {
        return "TextSection{" + "description='" + description + '\'' + '}';
    }
}
