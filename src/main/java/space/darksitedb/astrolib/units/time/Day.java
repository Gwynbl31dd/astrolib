package space.darksitedb.astrolib.units.time;

import space.darksitedb.astrolib.units.Unit;

public class Day extends Unit {

    public Day(double days) {
        super(days);
        if(days < 0) {
            throw new IllegalArgumentException("Time cannot be negative");
        }
    }
}
