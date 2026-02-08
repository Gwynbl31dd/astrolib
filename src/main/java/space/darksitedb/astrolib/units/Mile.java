package space.darksitedb.astrolib.units;

public class Mile implements Distance {

    private final double value;
    private static final double TO_KILOMETERS = 1.609344;

    public Mile(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }

    public Kilometer toKilometers() {
        return new Kilometer(value * TO_KILOMETERS);
    }

    public LightYear toLightYears() {
        // We get the conversion factor from LY, easier to maintain in one place
        return new LightYear(value * (1/new LightYear(1).toMiles().getValue()));
    }

    public Au toAu() {
        return new Au(value * (1/new Au(1).toMiles().getValue()));
    }

}
