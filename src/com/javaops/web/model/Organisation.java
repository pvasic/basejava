package com.javaops.web.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Vasichkin Pavel
 */
public class Organisation {
    private final String companyName;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String positionName;
    private final String responsibility;
    private final String url;

    public Organisation(String companyName, LocalDate startDate, LocalDate endDate, String positionName, String responsibility, String url) {
        Objects.requireNonNull(companyName, "companyName must not be null");
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        Objects.requireNonNull(positionName, "positionName must not be null");
        this.companyName = companyName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.positionName = positionName;
        this.responsibility = responsibility;
        this.url = url;
    }

    public String getCompanyName() {
        return companyName;
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

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Organisation that = (Organisation) o;
        return companyName.equals(that.companyName) && startDate.equals(that.startDate) && endDate.equals(that.endDate) && positionName.equals(that.positionName) && Objects.equals(responsibility, that.responsibility) && Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyName, startDate, endDate, positionName, responsibility, url);
    }

    @Override
    public String toString() {
        return "Organisation{" + "CompanyName='" + companyName + '\'' + ", startDate=" + startDate + ", endDate=" + endDate + ", positionName='" + positionName + '\'' + ", responsibility='" + responsibility + '\'' + ", url='" + url + '\'' + '}';
    }
}
