package space.darksitedb.astrolib.units;

public class LightYear implements Distance {

    private final double value;
    private static final double TO_MILES = 5.879e12;

    public LightYear(double value) {
        this.value = value;
    }

    @Override
    public double getValue() {
        return value;
    }

    public Mile toMiles() {
        return new Mile(value * TO_MILES);
    }

    public Parsec toParsecs() {
        return new Parsec(value * (1/new Parsec(1).toLightYears().getValue()));
    }

}
