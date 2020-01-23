package com.javaops.web.model;

import java.util.List;
import java.util.Objects;

/**
 * @author Vasichkin Pavel
 */
public class OrganisationSection implements Section {
    private final List<Organisation> organisations;

    public OrganisationSection(List<Organisation> organisations) {
        this.organisations = organisations;
    }

    public List<Organisation> getOrganisations() {
        return organisations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        OrganisationSection that = (OrganisationSection) o;
        return organisations.equals(that.organisations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organisations);
    }

    @Override
    public String toString() {
        return "OrganisationSection{" + "organisations=" + organisations + '}';
    }
}
