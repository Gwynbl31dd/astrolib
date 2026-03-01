package space.darksitedb.astrolib.units.time;

public class JulianDate {

    private final double value;

    public JulianDate(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public UT toDate() {
        double jd = value + 0.5;
        int z = (int) jd;
        double f = jd - z;
        int a = z;

        if (z >= 2299161) {
            int alpha = (int) ((z - 1867216.25) / 36524.25);
            a += 1 + alpha - alpha / 4;
        }

        int b = a + 1524;
        int c = (int) ((b - 122.1) / 365.25);
        int d = (int) (365.25 * c);
        int e = (int) ((b - d) / 30.6001);

        int day = (int) (b - d - (int) (30.6001 * e) + f);
        int month = (e < 14) ? e - 1 : e - 13;
        int year = (month > 2) ? c - 4716 : c - 4715;

        return new UT(new Year(year), new Month(month), new Day(day));
    }
}
