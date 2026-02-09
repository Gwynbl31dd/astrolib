package space.darksitedb.astrolib.units;

public class DegreeFahrenheit extends Unit implements Temperature {

    public DegreeFahrenheit(double value) {
        super(value);
        if(value < -459.67) {
            throw new IllegalArgumentException("Temperature cannot be below absolute zero");
        }
    }

    @Override
    public DegreeKelvin toKelvin() {
        return toCelsius().toKelvin();
    }

    @Override
    public DegreeCelsius toCelsius() {
        return new DegreeCelsius((value - 32) * 5 / 9);
    }

    @Override
    public DegreeFahrenheit toFahrenheit() {
        return this;
    }

}
