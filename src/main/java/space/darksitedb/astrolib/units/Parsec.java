package space.darksitedb.astrolib.units;

public class Parsec implements Distance {

    private final double value;
    private static final double TO_LIGHTYEARS = 3.26156;

    public Parsec(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }

    public LightYear toLightYears() {
        return new LightYear(value * TO_LIGHTYEARS);
    }

}
