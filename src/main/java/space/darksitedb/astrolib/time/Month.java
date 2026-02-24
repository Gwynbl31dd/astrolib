package space.darksitedb.astrolib.time;

public class Month {

    private final java.time.Month value;

    public Month(int month) {
        this.value = java.time.Month.of(month);
    }

    public int getValue() {
        return value.getValue();
    }

}
