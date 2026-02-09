package space.darksitedb.astrolib.units;

public abstract class Unit {

    protected final double value;

    public Unit(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

}
