package com.javaops.web.model;

import com.javaops.web.util.LocalDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
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
@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;

    private Link homePage;
    private List<Position> periods = new ArrayList<>();

    public Organization() {
    }

    public Organization(String name, String url, Position... periods) {
        this(new Link(name, url), Arrays.asList(periods));
    }

    public Organization(Link homePage, List<Position> periods) {
        Objects.requireNonNull(periods, "periods must not be null");
        this.homePage = homePage;
        this.periods = periods;
    }

    public Link getHomePage() {
        return homePage;
    }

    public List<Position> getPeriods() {
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
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Position implements Serializable {
        private static final long serialVersionUID = 1L;

        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate startDate;
        @XmlJavaTypeAdapter(LocalDateAdapter.class)
        private LocalDate endDate;
        private String positionName;
        private String responsibility;

        public Position() {
        }

        public Position(int startYear, Month startMonth, String title, String description) {
            this(of(startYear, startMonth), NOW, title, description);
        }

        public Position(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
            this(of(startYear, startMonth), of(endYear, endMonth), title, description);
        }

        public Position(LocalDate startDate, LocalDate endDate, String positionName, String responsibility) {
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
            Position that = (Position) o;
            return startDate.equals(that.startDate) && endDate.equals(that.endDate) && positionName.equals(that.positionName) && Objects.equals(responsibility, that.responsibility);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate, positionName, responsibility);
        }

        @Override
        public String toString() {
            return "Position{" + "startDate=" + startDate + "endDate=" + endDate + ", positionName='" + positionName + '\'' + ", responsibility='" + responsibility + '\'' + '}';
        }
    }

    /**
     * @author Vasichkin Pavel
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Link implements Serializable {
        private static final long serialVersionUID = 1L;

        private String name;
        private String url;

        public Link() {
        }

        public Link(String name, String url) {
            Objects.requireNonNull(name, "name must not be null");
            this.name = name;
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }

        @Override
        public String toString() {
            return "Link(" + name + ',' + url + ')';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            Link link = (Link) o;

            if (!name.equals(link.name))
                return false;
            return url != null ? url.equals(link.url) : link.url == null;

        }

        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + (url != null ? url.hashCode() : 0);
            return result;
        }
    }
}
