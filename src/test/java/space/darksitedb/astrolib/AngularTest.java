package space.darksitedb.astrolib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import space.darksitedb.astrolib.units.*;

import org.junit.jupiter.api.Test;

public class AngularTest {

    @Test
    void givenDegree_whenConvertToRadian_thenCorrect() {
        Degree degree = new Degree(180);
        Radian radian = degree.toRadians();
        assertEquals(Math.PI, radian.getValue(), 1e-10);
    }

    @Test
    void givenRadian_whenConvertToRadian_thenSame() {
        Radian radian = new Radian(Math.PI);
        Radian converted = radian.toRadians();
        assertEquals(radian.getValue(), converted.getValue(), 1e-10);
    }

    @Test
    void givenRadian_whenConvertToDegree_thenCorrect() {
        Radian radian = new Radian(2.5);
        Degree degree = radian.toDegrees();
        assertEquals(143.2394487827058, degree.getValue(), 1e-10);
    }

}
