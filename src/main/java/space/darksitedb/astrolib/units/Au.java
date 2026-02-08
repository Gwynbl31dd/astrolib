package space.darksitedb.astrolib.units;

public class Au implements Distance {

    private final double value;

    public Au(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }

    public Mile toMiles() {
        return new Mile(value * 9.296e+7);
    }

}
