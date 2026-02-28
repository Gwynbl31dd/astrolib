package space.darksitedb.astrolib.units;

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
        int Z = (int) jd;
        double F = jd - Z;
        int A = Z;

        if (Z >= 2299161) {
            int alpha = (int) ((Z - 1867216.25) / 36524.25);
            A += 1 + alpha - alpha / 4;
        }

        int B = A + 1524;
        int C = (int) ((B - 122.1) / 365.25);
        int D = (int) (365.25 * C);
        int E = (int) ((B - D) / 30.6001);

        int day = (int) (B - D - (int) (30.6001 * E) + F);
        int month = (E < 14) ? E - 1 : E - 13;
        int year = (month > 2) ? C - 4716 : C - 4715;

        return new UT(new Year(year), new Month(month), new Day(day));
    }
}
