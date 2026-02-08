package space.darksitedb.astrolib.units;

public class Millimeter {

    private final double value;

    public Millimeter(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public Inch toInches() {
        return new Inch(value / 25.4);
    }
}
