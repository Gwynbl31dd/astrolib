package space.darksitedb.astrolib.units;

public class Inch {

    private final double value;

    public Inch(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public Millimeter toMillimeters() {
        return new Millimeter(value * 25.4);
    }
}
