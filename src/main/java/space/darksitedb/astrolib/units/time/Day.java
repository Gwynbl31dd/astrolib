package space.darksitedb.astrolib.units.time;

public class Day {

    private final int value;

    public Day(int day) {
        if (day < 1) {
            throw new IllegalArgumentException("Day must be greater than 0");
        }
        this.value = day;
    }

    public int getValue() {
        return value;
    }

}
