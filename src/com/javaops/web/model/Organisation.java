package com.javaops.web.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Vasichkin Pavel
 */
public class Organisation {
    private final Link homePage;

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String positionName;
    private final String responsibility;

    public Organisation(String name, String url, LocalDate startDate, LocalDate endDate, String positionName, String responsibility) {
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        Objects.requireNonNull(positionName, "positionName must not be null");
        this.homePage = new Link(name, url);
        this.startDate = startDate;
        this.endDate = endDate;
        this.positionName = positionName;
        this.responsibility = responsibility;
    }

    public Link getHomePage() {
        return homePage;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getPositionName() {
        return positionName;
    }

    public String getResponsibility() {
        return responsibility;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Organisation that = (Organisation) o;
        return homePage.equals(that.homePage) && startDate.equals(that.startDate) && endDate.equals(that.endDate) && positionName.equals(that.positionName) && Objects.equals(responsibility, that.responsibility);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, startDate, endDate, positionName, responsibility);
    }

    @Override
    public String toString() {
        return "Organisation{" + "homePage='" + homePage + '\'' + ", startDate=" + startDate + ", endDate=" + endDate + ", positionName='" + positionName + '\'' + ", responsibility='" + responsibility + '\'' + '}';
    }
}
