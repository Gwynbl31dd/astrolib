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

    @Test
    void givenDegree_whenConvertToDegree_thenSame() {
        Degree degree = new Degree(45);
        Degree converted = degree.toDegrees();
        assertEquals(degree.getValue(), converted.getValue(), 1e-10);
    }

    @Test
    void givenDegree_whenConvertToHour_thenCorrect() {
        Degree degree = new Degree(156.3);
        Hour hour = degree.toHours();
        assertEquals(10.42, hour.getValue(), 1e-10);
    }

    @Test
    void givenHours_whenConvertToDegree_thenCorrect() {
        Hour hour = new Hour(2);
        Degree degree = hour.toDegrees();
        assertEquals(30, degree.getValue(), 1e-10);
    }

    @Test
    void givenDms_whenConvertToDegree_thenCorrect() {
        Dms dms = new Dms(13, 04, 10);
        Degree degree = dms.toDegrees();
        assertEquals(13.069444444444445, degree.getValue(), 1e-10);
    }

    @Test
    void givenLargeDms_whenConvertToDegree_thenCorrect() {
        Dms dms = new Dms(300, 20, 0);
        Degree degree = dms.toDegrees();
        assertEquals(300.3333333333333, degree.getValue(), 1e-10);
    }

    @Test
    void givenNegativeDegree_whenConvertToDms_thenCorrect() {
        Degree degree = new Degree(-0.508333);
        Dms dms = degree.toDms();
        assertEquals("-0° 30' 30.00\"", dms.toString());
    }

    @Test
    void givenPositiveDegree_whenConvertToDms_thenCorrect() {
        Degree degree = new Degree(10.2958);
        Dms dms = degree.toDms();
        assertEquals("10° 17' 44.88\"", dms.toString());
    }


}
