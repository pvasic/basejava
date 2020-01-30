package com.javaops.web.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

/**
 * @author Vasichkin Pavel
 */
public class BusyPeriod {
    private final LocalDate startDate;
    private final Period period;
    private final String positionName;
    private final String responsibility;

    public BusyPeriod(LocalDate startDate, Period period, String positionName, String responsibility) {
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(period, "period must not be null");
        Objects.requireNonNull(positionName, "positionName must not be null");
        this.startDate = startDate;
        this.period = period;
        this.positionName = positionName;
        this.responsibility = responsibility;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Period getPeriod() {
        return period;
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
        return startDate.equals(that.startDate) && period.equals(that.period) && positionName.equals(that.positionName) && Objects.equals(responsibility, that.responsibility);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, period, positionName, responsibility);
    }

    @Override
    public String toString() {
        return "BusyPeriod{" + "startDate=" + startDate + "period=" + period + ", positionName='" + positionName + '\'' + ", responsibility='" + responsibility + '\'' + '}';
    }
}
