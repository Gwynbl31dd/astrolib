package space.darksitedb.astrolib.time;

public class Year {

    private final java.time.Year value;

    public Year(int value) {
        this.value = java.time.Year.of(value);
    }

    public int getValue() {
        return value.getValue();
    }

    public boolean isLeapYear() {
        return value.isLeap();
    }
}

