package com.javaops.web.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Vasichkin Pavel
 */
public class BusyPeriod {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String positionName;
    private final String responsibility;

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
