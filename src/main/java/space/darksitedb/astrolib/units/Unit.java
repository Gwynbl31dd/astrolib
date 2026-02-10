package space.darksitedb.astrolib.units;

/**
 * Abstract class representing a unit of measurement. It provides a common structure for all units, 
 * including a value and a method to retrieve it.
 * 
 * Value is stored as a double to allow for a wide range of measurements, 
 * including very small and very large values, which are common in astronomical contexts.
 */
public abstract class Unit {

    protected final double value;

    public Unit(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

}
