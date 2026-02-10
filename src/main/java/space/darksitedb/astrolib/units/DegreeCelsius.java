package space.darksitedb.astrolib.units;

public class DegreeCelsius extends Unit implements Temperature {

    public DegreeCelsius(double value) throws IllegalArgumentException {
        super(value);
        if (value < -273.15) {
            throw new IllegalArgumentException("Temperature cannot be below absolute zero");
        }
    }

    @Override
    public DegreeKelvin toKelvin() {
        return new DegreeKelvin(value + 273.15);
    }

    @Override
    public DegreeCelsius toCelsius() {
        return this;
    }

    @Override
    public DegreeFahrenheit toFahrenheit() {
        return new DegreeFahrenheit(value * 9 / 5 + 32);
    }

}
