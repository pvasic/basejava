package com.javaops.web.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.javaops.web.util.DateUtil.NOW;
import static com.javaops.web.util.DateUtil.of;

/**
 * @author Vasichkin Pavel
 */
public class Organization {
    private final Link homePage;
    private List<BusyPeriod> periods = new ArrayList<>();

    public Organization(String name, String url, BusyPeriod... periods) {
        this(new Link(name, url), Arrays.asList(periods));
    }

    public Organization(Link homePage, List<BusyPeriod> periods) {
        Objects.requireNonNull(periods, "periods must not be null");
        this.homePage = homePage;
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
        Organization that = (Organization) o;
        return Objects.equals(homePage, that.homePage) && periods.equals(that.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homePage, periods);
    }

    @Override
    public String toString() {
        return "Organization{" + "homePage=" + homePage + ", periods=" + periods + '}';
    }

    /**
     * @author Vasichkin Pavel
     */
    public static class BusyPeriod {
        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String positionName;
        private final String responsibility;

        public BusyPeriod(int startYear, Month startMonth, String title, String description) {
            this(of(startYear, startMonth), NOW, title, description);
        }

        public BusyPeriod(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
            this(of(startYear, startMonth), of(endYear, endMonth), title, description);
        }

        public BusyPeriod(LocalDate startDate, LocalDate endDate, String positionName, String responsibility) {
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(endDate, "endDate must not be null");
            Objects.requireNonNull(positionName, "positionName must not be null");
            this.startDate = startDate;
            this.endDate = endDate;
            this.positionName = positionName;
            this.responsibility = responsibility;
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
            BusyPeriod that = (BusyPeriod) o;
            return startDate.equals(that.startDate) && endDate.equals(that.endDate) && positionName.equals(that.positionName) && Objects.equals(responsibility, that.responsibility);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate, positionName, responsibility);
        }

        @Override
        public String toString() {
            return "BusyPeriod{" + "startDate=" + startDate + "endDate=" + endDate + ", positionName='" + positionName + '\'' + ", responsibility='" + responsibility + '\'' + '}';
        }
    }
}
