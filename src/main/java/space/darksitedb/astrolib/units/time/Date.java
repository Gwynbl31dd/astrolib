package space.darksitedb.astrolib.units.time;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The Date class represents a specific date and time, combining the components
 * of year, month, day, hour, minute, and second.
 * It provides methods to retrieve the individual components of the date and
 * time, as well as additional information such as the day of the week and the
 * day of the year.
 */
public abstract class Date {

    /**
     * Result of day boundary crossing calculation containing adjusted date and
     * time.
     */
    protected record DateTimeCrossing(LocalDateTime dateTime, Hms time) {
    }

    private final java.time.LocalDate value;
    private final Hour hour;
    private final Minute minute;
    private final Second second;

    public Date(Year year, Month month, Day day, Hour hour, Minute minute, Second second) {

        if (day.getValue() <= 0) {
            throw new IllegalArgumentException("Time cannot be negative");
        }

        if (hour.getValue() < 0 || minute.getValue() < 0 || second.getValue() < 0) {
            throw new IllegalArgumentException("Time cannot be negative");
        }

        // If day, hour or minute has a fractional part, we throw an exception
        // as this is not supported
        if (day.getValue() != Math.floor(day.getValue()) || hour.getValue() != Math.floor(hour.getValue())
                || minute.getValue() != Math.floor(minute.getValue())) {
            throw new IllegalArgumentException("Fractional day, hour or minute is not supported");
        }

        if (day.getValue() > 31) {
            // If day has a fractional part, we remove it and add them to hours minutes and
            // seconds
            double fractionalDay = day.getValue() - Math.floor(day.getValue());
            double totalHours = fractionalDay * 24;
            double hours = Math.floor(totalHours);
            double totalMinutes = (totalHours - hours) * 60;
            double minutes = Math.floor(totalMinutes);
            double seconds = (totalMinutes - minutes) * 60;

            day = new Day(Math.floor(day.getValue()));
            hour = new Hour(hour.getValue() + hours);
            minute = new Minute(minute.getValue() + minutes);
            second = new Second(second.getValue() + seconds);

            // If day greater than a month, interpret as day-of-year and calculate the
            // actual date
            LocalDate baseDate = LocalDate.of(year.getValue(), 1, 1);
            this.value = baseDate.plusDays((int) day.getValue() - 1);
        } else {
            this.value = LocalDate.of(year.getValue(), month.getValue(), (int) day.getValue());
        }

        if (hour.getValue() >= 24 || minute.getValue() >= 60 || second.getValue() >= 60) {
            throw new IllegalArgumentException("Invalid time: hour must be < 24, minute and second must be < 60");
        }

        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    protected DateTimeCrossing getDayBondaryCrossing(double hours) {

        LocalDateTime dateTime = LocalDateTime.of(
                getYear().getValue(), getMonth().getValue(), (int) getDay().getValue(), 0, 0, 0);
        // Handle day boundary crossings
        while (hours < 0) {
            hours += 24;
            dateTime = dateTime.minusDays(1);
        }
        while (hours >= 24) {
            hours -= 24;
            dateTime = dateTime.plusDays(1);
        }

        return new DateTimeCrossing(dateTime, new Hms(new Hour(hours)));
    }

    public java.time.DayOfWeek getDayOfTheWeek() {
        return value.getDayOfWeek();
    }

    public Integer getDayOfYear() {
        return value.getDayOfYear();
    }

    public Year getYear() {
        return new Year(value.getYear());
    }

    public Month getMonth() {
        return new Month(value.getMonthValue());
    }

    public Day getDay() {
        return new Day(value.getDayOfMonth());
    }

    public Hour getHour() {
        return hour;
    }

    public Minute getMinute() {
        return minute;
    }

    public Second getSecond() {
        return second;
    }
}
