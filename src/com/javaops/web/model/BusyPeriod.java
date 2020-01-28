package com.javaops.web.model;

import java.time.Period;
import java.util.Objects;

/**
 * @author Vasichkin Pavel
 */
public class BusyPeriod {
    private final Period period;
    private final String positionName;
    private final String responsibility;

    public BusyPeriod(Period period, String positionName, String responsibility) {
        Objects.requireNonNull(period, "period must not be null");
        Objects.requireNonNull(positionName, "positionName must not be null");
        this.period = period;
        this.positionName = positionName;
        this.responsibility = responsibility;
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
        return period.equals(that.period) && positionName.equals(that.positionName) && Objects.equals(responsibility, that.responsibility);
    }

    @Override
    public int hashCode() {
        return Objects.hash(period, positionName, responsibility);
    }

    @Override
    public String toString() {
        return "BusyPeriod{" + "period=" + period + ", positionName='" + positionName + '\'' + ", responsibility='" + responsibility + '\'' + '}';
    }
}
