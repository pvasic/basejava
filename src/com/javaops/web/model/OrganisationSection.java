package com.javaops.web.model;

import java.util.List;

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
    public String toString() {
        return "OrganisationSection{" + "organisations=" + organisations + '}';
    }
}
