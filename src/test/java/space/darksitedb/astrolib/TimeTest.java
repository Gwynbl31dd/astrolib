package space.darksitedb.astrolib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import space.darksitedb.astrolib.units.*;

import org.junit.jupiter.api.Test;

public class TimeTest {

    @Test
    void givenHms_whenConvertToHours_thenCorrect() {
        Hms hms = new Hms(new Hour(2), new Minute(30), new Second(45));
        Hour hour = hms.toHours();
        assertEquals(2.5125, hour.getValue(), 1e-10);
    }

    @Test
    void givenHms_whenConvertToHms_thenSame() {
        Hms hms = new Hms(new Hour(1), new Minute(15), new Second(30));
        Hms converted = hms.toHms();
        assertEquals(hms.toHours().getValue(), converted.toHours().getValue(), 1e-10);
    }

    @Test
    void givenHours_whenConvertToHms_thenCorrect() {
        Hour hour = new Hour(20.352);
        Hms hms = hour.toHms();
        assertEquals("20:21:07.2", hms.toString());
    }

}
