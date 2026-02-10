package space.darksitedb.astrolib.units;

public class DegreeKelvin extends Unit implements Temperature {

    public DegreeKelvin(double value) throws IllegalArgumentException {
        super(value);
        if (value < 0) {
            throw new IllegalArgumentException("Temperature cannot be below absolute zero");
        }
    }

    @Override
    public DegreeKelvin toKelvin() {
        return this;
    }

    @Override
    public DegreeCelsius toCelsius() {
        return new DegreeCelsius(value - 273.15);
    }

    @Override
    public DegreeFahrenheit toFahrenheit() {
        return toCelsius().toFahrenheit();
    }

}
