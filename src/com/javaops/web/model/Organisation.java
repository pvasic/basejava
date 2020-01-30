package com.javaops.web.model;

import java.util.List;
import java.util.Objects;

/**
 * @author Vasichkin Pavel
 */
public class Organisation {
    private final Link homePage;

    private final List<BusyPeriod> periods;

    public Organisation(String name, String url, List<BusyPeriod> periods) {
        Objects.requireNonNull(periods, "periods must not be null");
        this.homePage = new Link(name, url);
        this.periods = periods;
    }

    public Link getHomePage() {
        return homePage;
    }

    public List<BusyPeriod> getPeriods() {
        return periods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Organisation that = (Organisation) o;
        return Objects.equals(homePage, that.homePage) && periods.equals(that.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, periods);
    }

    @Override
    public String toString() {
        return "Organisation{" + "homePage=" + homePage + ", periods=" + periods + '}';
    }
}
