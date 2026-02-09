package space.darksitedb.astrolib;

import static org.junit.jupiter.api.Assertions.assertAll;
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

    @Test
    void givenRadian_whenConvertToHours_thenCorrect() {
        Radian radian = new Radian(Math.PI / 2);
        Hour hour = radian.toHours();
        assertEquals(6, hour.getValue(), 1e-10);
    }

    @Test
    void givenRadian_whenConvertToDms_thenCorrect() {
        Radian radian = new Radian(1);
        Dms dms = radian.toDms();
        assertEquals("57° 17' 44.81\"", dms.toString());
    }

    @Test
    void givenArcMinute_whenConvertToDegrees_thenCorrect() {
        ArcMinute arcMinute = new ArcMinute(30);
        Degree degree = arcMinute.toDegrees();
        assertEquals(0.5, degree.getValue(), 1e-10);
    }

    @Test
    void givenArcMinutes_whenConvertToHours_thenCorrect() {
        ArcMinute arcMinute = new ArcMinute(120);
        Hour hour = arcMinute.toHours();
        assertEquals(0.13333333333333333, hour.getValue(), 1e-10);
    }

    @Test
    void givenArcMinute_whenConvertToDms_thenCorrect() {
        ArcMinute arcMinute = new ArcMinute(90);
        Dms dms = arcMinute.toDms();
        assertEquals("1° 30' 0.00\"", dms.toString());
    }

    @Test
    void givenArcMinute_whenConvertToRadians_thenCorrect() {
        ArcMinute arcMinute = new ArcMinute(15);
        Radian radian = arcMinute.toRadians();
        assertEquals(0.004363323129985823, radian.getValue(), 1e-10);
    }

    @Test
    void givenArcSecond_whenConvertToDegrees_thenCorrect() {
        ArcSecond arcSecond = new ArcSecond(45);
        Degree degree = arcSecond.toDegrees();
        assertEquals(0.0125, degree.getValue(), 1e-10);
    }

    @Test
    void givenArcSecond_whenConvertToHours_thenCorrect() {
        ArcSecond arcSecond = new ArcSecond(90);
        Hour hour = arcSecond.toHours();
        assertEquals(0.0016666666666666666, hour.getValue(), 1e-10);
    }

    @Test
    void givenArcSecond_whenConvertToDms_thenCorrect() {
        ArcSecond arcSecond = new ArcSecond(120);
        Dms dms = arcSecond.toDms();
        assertEquals("0° 2' 0.00\"", dms.toString());
    }

    @Test
    void givenArcSecond_whenConvertToRadians_thenCorrect() {
        ArcSecond arcSecond = new ArcSecond(30);
        Radian radian = arcSecond.toRadians();
        assertEquals(0.0001454441043328609, radian.getValue(), 1e-10);
    }

    @Test
    void givenDms_whenConvertToHours_thenCorrect() {
        Dms dms = new Dms(15, 30, 0);
        Hour hour = dms.toHours();
        assertEquals(1.0333333333333332, hour.getValue(), 1e-10);
    }

    @Test
    void givenDms_whenGetIfNegative_thenCorrect() {
        Dms dms = new Dms(-10, 20, 30);
        assertEquals(true, dms.isNegative());
    }

    @Test
    void givenDms_whenGetValues_thenCorrect() {
        Dms dms = new Dms(10, 20, 30);
        assertAll(
            () -> assertEquals(10, dms.getDegrees()),
            () -> assertEquals(20, dms.getMinutes()),
            () -> assertEquals(30, dms.getSeconds(), 1e-10)
        );
    }

    @Test
    void givenDms_whenConvertToDms_thenSame() {
        Dms dms = new Dms(5, 15, 45);
        Dms converted = dms.toDms();
        assertEquals(dms.toDegrees().getValue(), converted.toDegrees().getValue(), 1e-10);
    }

    @Test
    void givenDms_whenConvertToRadian_thenCorrect() {
        Dms dms = new Dms(30, 0, 0);
        Radian radian = dms.toRadians();
        assertEquals(Math.PI / 6, radian.getValue(), 1e-10);
    }


}
