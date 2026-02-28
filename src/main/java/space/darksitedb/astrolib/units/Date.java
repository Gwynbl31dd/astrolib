package space.darksitedb.astrolib.units;

public class Date {

    private final java.time.LocalDate value;
    private final Hour hour;
    private final Minute minute;
    private final Second second;

    public Date(Year year, Month month, Day day, Hour hour, Minute minute, Second second) {

        if(day.getValue() > 31) {
            // If day greater than a month, interpret as day-of-year and calculate the actual date
            java.time.LocalDate baseDate = java.time.LocalDate.of(year.getValue(), 1, 1);
            this.value = baseDate.plusDays(day.getValue() - 1);
        } else {
            this.value = java.time.LocalDate.of(year.getValue(), month.getValue(), day.getValue());
        }

        this.hour = hour;
        this.minute = minute;
        this.second = second;
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
