package space.darksitedb.astrolib.units;

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

        if (day.getValue() > 31) {
            // If day greater than a month, interpret as day-of-year and calculate the
            // actual date
            LocalDate baseDate = LocalDate.of(year.getValue(), 1, 1);
            this.value = baseDate.plusDays(day.getValue() - 1);
        } else {
            this.value = LocalDate.of(year.getValue(), month.getValue(), day.getValue());
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
                getYear().getValue(), getMonth().getValue(), getDay().getValue(), 0, 0, 0);
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
