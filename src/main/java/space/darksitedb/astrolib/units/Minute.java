package space.darksitedb.astrolib.units;

public class Minute extends Unit implements Time {

    public Minute(double value) {
        super(value);
    }

    @Override
    public Degree toDegrees() {
       throw new UnsupportedOperationException("Unimplemented method 'toDegrees'");
    }

    @Override
    public Hour toHours() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toHours'");
    }

    @Override
    public Hms toHms() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toHms'");
    }

}
