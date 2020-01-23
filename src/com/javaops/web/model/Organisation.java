package com.javaops.web.model;

import java.time.LocalDate;

/**
 * @author Vasichkin Pavel
 */
public class Organisation {
    private final String CompanyName;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String positionName;
    private final String responsibility;
    private final String url;

    public Organisation(String companyName, LocalDate startDate, LocalDate endDate, String positionName, String responsibility, String url) {
        CompanyName = companyName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.positionName = positionName;
        this.responsibility = responsibility;
        this.url = url;
    }

    public String getCompanyName() {
        return CompanyName;
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
    public String toString() {
        return "Organisation{" + "CompanyName='" + CompanyName + '\'' + ", startDate=" + startDate + ", endDate=" + endDate + ", positionName='" + positionName + '\'' + ", responsibility='" + responsibility + '\'' + ", url='" + url + '\'' + '}';
    }
}
