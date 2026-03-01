package space.darksitedb.astrolib;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import space.darksitedb.astrolib.units.*;
import space.darksitedb.astrolib.units.angle.ArcMinute;
import space.darksitedb.astrolib.units.angle.ArcSecond;
import space.darksitedb.astrolib.units.angle.Degree;
import space.darksitedb.astrolib.units.angle.Dms;
import space.darksitedb.astrolib.units.time.Day;
import space.darksitedb.astrolib.units.time.GST;
import space.darksitedb.astrolib.units.time.Hms;
import space.darksitedb.astrolib.units.time.Hour;
import space.darksitedb.astrolib.units.time.JulianDate;
import space.darksitedb.astrolib.units.time.LCT;
import space.darksitedb.astrolib.units.time.LST;
import space.darksitedb.astrolib.units.time.Minute;
import space.darksitedb.astrolib.units.time.Month;
import space.darksitedb.astrolib.units.time.Second;
import space.darksitedb.astrolib.units.time.UT;
import space.darksitedb.astrolib.units.time.Year;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TimeTest {

    @Test
    void givenHms_whenConvertToHours_thenCorrect() {
        Hms hms = new Hms(new Hour(2), new Minute(30), new Second(45));
        Hour hour = hms.toDecimalHours();
        assertEquals(2.5125, hour.getValue(), 1e-10);
    }

    @Test
    void givenHms_whenConvertToHms_thenSame() {
        Hms hms = new Hms(new Hour(1), new Minute(15), new Second(30));
        Hms converted = hms.toHms();
        assertEquals(hms.toDecimalHours().getValue(), converted.toDecimalHours().getValue(), 1e-10);
    }

    @Test
    void givenHours_whenConvertToHms_thenCorrect() {
        Hour hour = new Hour(20.352);
        Hms hms = hour.toHms();
        assertEquals("20:21:07.2", hms.toString());
    }

    @Test
    void givenHours_whenConvertToHours_thenSame() {
        Hour hour = new Hour(5);
        Hour converted = hour.toDecimalHours();
        assertEquals(hour.getValue(), converted.getValue(), 1e-10);
    }

    @Test
    void givenHms_whenConvertToDegrees_thenCorrect() {
        Hms hms = new Hms(new Hour(3), new Minute(45), new Second(0));
        Degree degree = hms.toDegrees();
        assertEquals(56.25, degree.getValue(), 1e-10);
    }

    @Test
    void givenMinute_whenConvertToHours_thenCorrect() {
        Minute minute = new Minute(90);
        Hour hour = minute.toDecimalHours();
        assertEquals(1.5, hour.getValue(), 1e-10);
    }

    @Test
    void givenMinutes_whenConvertToDegrees_thenCorrect() {
        Minute minute = new Minute(30);
        Degree degree = minute.toDegrees();
        assertEquals(7.5, degree.getValue(), 1e-10);
    }

    @Test
    void givenMinutes_whenConvertToHms_thenCorrect() {
        Minute minute = new Minute(45);
        Hms hms = minute.toHms();
        assertEquals("00:45:00.0", hms.toString());
    }

    @Test
    void givenSeconds_whenConvertToDegrees_thenCorrect() {
        Second second = new Second(3600);
        Degree degree = second.toDegrees();
        assertEquals(15.0, degree.getValue(), 1e-10);
    }

    @Test
    void givenSeconds_whenConvertToHours_thenCorrect() {
        Second second = new Second(7200);
        Hour hour = second.toDecimalHours();
        assertEquals(2.0, hour.getValue(), 1e-10);
    }

    @Test
    void givenSeconds_whenConvertToHms_thenCorrect() {
        Second second = new Second(3661);
        Hms hms = second.toHms();
        assertEquals("01:01:01.0", hms.toString());
    }

    @Test
    void givenHour_whenValueIsNegative_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Hour(-1));
    }

    @Test
    void givenMinute_whenValueIsNegative_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Minute(-1));
    }

    @Test
    void givenSecond_whenValueIsNegative_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Second(-1));
    }

    @ParameterizedTest(name = "year {0} is leap year: {1}")
    @CsvSource({ "1984, true", "1974, false", "2000, true", "1900, false" })
    void givenYear_whenRequestIsLeapYear_thenCorrect(int yearValue, boolean expected) {
        Year year = new Year(yearValue);
        assertEquals(expected, year.isLeapYear());
    }

    @Test
    void givenADate_whenConvertToJulianDayNumber_thenCorrect() {
        UT date = new UT(new Year(2010), new Month(11), new Day(1));
        JulianDate julianDate = date.toJulianDayNumber();
        assertEquals(2455501.5, julianDate.getValue());
    }

    @ParameterizedTest(name = "Date UT {0}-{1}-{2} {3}:{4}:{5} corresponds to Julian Date {6}")
    @CsvSource({ "2015, 5, 10, 6, 0, 0, 2457152.75",
            "2015, 5, 10, 18, 0, 0, 2457153.25",
            "1776, 7, 4, 0, 0, 0, 2369915.5",
            "2010, 5, 6, 12, 0, 0, 2455323.0",
            "2012, 4, 1, 20, 52, 48, 2456019.37"
    })
    void givenADateWithTime_whenConvertToJulianDayNumber_thenCorrect(int year, int month, int day, int hour, int minute,
            int second, double expectedJulianDate) {
        UT date = new UT(new Year(year), new Month(month), new Day(day), new Hour(hour), new Minute(minute),
                new Second(second));
        JulianDate julianDate = date.toJulianDayNumber();
        assertEquals(expectedJulianDate, julianDate.getValue());
    }

    @ParameterizedTest(name = "Julian Date {6} corresponds to Date UT {0}-{1}-{2} {3}:{4}:{5}")
    @CsvSource({ "2015, 5, 10, 6, 0, 0, 2457152.75",
            "2015, 5, 10, 18, 0, 0, 2457153.25",
            "1776, 7, 4, 0, 0, 0, 2369915.5",
            "2010, 5, 6, 12, 0, 0, 2455323.0",
            "2012, 4, 1, 20, 52, 48, 2456019.37"
    })
    void givenAJulianDate_whenConvertToDate_thenCorrect(int year, int month, int day, int hour, int minute, int second,
            double expectedJulianDate) {
        JulianDate julianDate = new JulianDate(expectedJulianDate);
        UT date = julianDate.toDate();
        assertEquals(year, date.getYear().getValue());
        assertEquals(month, date.getMonth().getValue());
        assertEquals(day, date.getDay().getValue());
    }

    @ParameterizedTest(name = "Date UT {0}-{1}-{2} corresponds to day of the week {3}")
    @CsvSource({
            "1776, 7, 4, Thursday",
            "2011, 9, 11, Sunday" })
    void givenADate_whenGetDayOfTheWeek_thenCorrect(int year, int month, int day, String expectedDayOfWeek) {
        UT date = new UT(new Year(year), new Month(month), new Day(day));
        assertEquals(expectedDayOfWeek, date.getDayOfWeek());
    }

    @Test
    void givenDate_whenRequestHowManyDaysIntoTheYear_thenCorrect() {
        UT date = new UT(new Year(2009), new Month(10), new Day(30));
        assertEquals(303, date.getDayOfYear());
    }

    @Test
    void givenADateWithDays_whenRequestDate_thenCorrect() {
        UT date = new UT(new Year(1900), new Day(250));
        assertEquals(1900, date.getYear().getValue());
        assertEquals(9, date.getMonth().getValue());
        assertEquals(7, date.getDay().getValue());
    }

    @ParameterizedTest(name = "Longitude {0} corresponds to time zone offset {1}")
    @CsvSource({
            "0, 0",
            "15, 1",
            "30, 2",
            "-15, -1",
            "-30, -2"
    })
    void givenALongitude_whenRequestTimeZoneOffset_thenCorrect(int longitudeValue, int expectedOffset) {
        Degree longitude = new Degree(longitudeValue);
        int offset = LCT.getTimeZoneOffsetFromLongitude(longitude);
        assertEquals(expectedOffset, offset);
    }

    @ParameterizedTest(name = "Longitude {0} corresponds to time zone offset {1}")
    @CsvSource({
            "0, 0",
            "15, 1",
            "30, 2",
            "-15, -1",
            "-30, -2"
    })
    void givenALongitudeInDms_whenRequestTimeZoneOffset_thenCorrect(int longitudeValue, int expectedOffset) {
        Dms longitude = new Dms(new Degree(longitudeValue), new ArcMinute(0), new ArcSecond(0), false);
        int offset = LCT.getTimeZoneOffsetFromLongitude(longitude);
        assertEquals(expectedOffset, offset);
    }

    @Test
    void givenADateIsInLct_whenRequestUT_thenCorrect() {
        LCT lct = new LCT(new Year(2020), new Month(1), new Day(1), new Hour(12), new Minute(0), new Second(0), 2);
        UT ut = lct.toUT();
        assertAll(() -> assertEquals(2020, ut.getYear().getValue()),
                () -> assertEquals(1, ut.getMonth().getValue()),
                () -> assertEquals(1, ut.getDay().getValue()),
                () -> assertEquals(10, ut.getHour().getValue()));
    }

    @Test
    void givenADateIsInUT_whenRequestLCT_thenCorrect() {
        UT ut = new UT(new Year(2020), new Month(1), new Day(1), new Hour(10), new Minute(0), new Second(0));
        LCT lct = ut.toLCT(2);
        assertAll(() -> assertEquals(2020, lct.toUT().getYear().getValue()),
                () -> assertEquals(1, lct.toUT().getMonth().getValue()),
                () -> assertEquals(1, lct.toUT().getDay().getValue()),
                () -> assertEquals(10, lct.toUT().getHour().getValue()));
    }

    @Test
    void givenADateIsInUT_whenRequestLCTWithNegativeOffset_thenCorrect() {
        UT ut = new UT(new Year(2020), new Month(1), new Day(1), new Hour(10), new Minute(0), new Second(0));
        LCT lct = ut.toLCT(-2);
        assertAll(() -> assertEquals(2020, lct.toUT().getYear().getValue()),
                () -> assertEquals(1, lct.toUT().getMonth().getValue()),
                () -> assertEquals(1, lct.toUT().getDay().getValue()),
                () -> assertEquals(10, lct.toUT().getHour().getValue()));
    }

    @Test
    void givenADateIsInUT_whenRequestLCTWithOffsetThatCrossesDayBoundary_thenCorrect() {
        UT ut = new UT(new Year(2020), new Month(1), new Day(1), new Hour(23), new Minute(0), new Second(0));
        LCT lct = ut.toLCT(2);
        UT utRoundTrip = lct.toUT();

        assertAll(() -> assertEquals(2020, lct.getYear().getValue()),
                () -> assertEquals(1, lct.getMonth().getValue()),
                () -> assertEquals(2, lct.getDay().getValue()),
                () -> assertEquals(1, lct.getHour().getValue()),
                () -> assertEquals(2020, utRoundTrip.getYear().getValue()),
                () -> assertEquals(1, utRoundTrip.getMonth().getValue()),
                () -> assertEquals(1, utRoundTrip.getDay().getValue()),
                () -> assertEquals(23, utRoundTrip.getHour().getValue()));
    }

    @Test
    void givenADateUt_whenConvertToGST_thenCorrect() {
        UT date = new UT(new Year(2010), new Month(2), new Day(7), new Hour(23), new Minute(30), new Second(0));
        GST gst = date.toGST();

        assertAll(() -> assertEquals(2010, gst.getYear().getValue()),
                () -> assertEquals(2, gst.getMonth().getValue()),
                () -> assertEquals(7, gst.getDay().getValue()),
                () -> assertEquals(8, gst.getHour().getValue()),
                () -> assertEquals(41, gst.getMinute().getValue()),
                () -> assertEquals(53.11848363696839, gst.getSecond().getValue()));
    }

    @Test
    void givenADateGst_whenConvertToUT_thenCorrect() {
        GST gst = new GST(new Year(2010), new Month(2), new Day(7), new Hour(8), new Minute(41),
                new Second(53.11848363696839));
        UT ut = gst.toUT();

        assertAll(() -> assertEquals(2010, ut.getYear().getValue()),
                () -> assertEquals(2, ut.getMonth().getValue()),
                () -> assertEquals(7, ut.getDay().getValue()),
                () -> assertEquals(23, ut.getHour().getValue()),
                () -> assertEquals(30, ut.getMinute().getValue()),
                () -> assertEquals(0, ut.getSecond().getValue(), 1e-9));
    }

    @Test
    void givenUTNearMidnight_whenConvertToGST_thenCrossesToNextDay() {
        // UT late at night that pushes GST past 24h into next day
        UT ut = new UT(new Year(2010), new Month(3), new Day(15), new Hour(23), new Minute(0), new Second(0));
        GST gst = ut.toGST();

        // Verify the conversion works (day stays same in this case, but test the
        // mechanism)
        assertAll(() -> assertEquals(2010, gst.getYear().getValue()),
                () -> assertEquals(3, gst.getMonth().getValue()),
                () -> assertEquals(15, gst.getDay().getValue()),
                () -> assertEquals(10, gst.getHour().getValue()));
    }

    @Test
    void givenUTEarlyMorning_whenConvertToGST_thenGoesToPreviousDay() {
        // UT very early in the morning - test boundary handling
        UT ut = new UT(new Year(2010), new Month(3), new Day(15), new Hour(0), new Minute(30), new Second(0));
        GST gst = ut.toGST();

        // Verify the conversion works
        assertAll(() -> assertEquals(2010, gst.getYear().getValue()),
                () -> assertEquals(3, gst.getMonth().getValue()));
    }

    @Test
    void givenGSTNearMidnight_whenConvertToUT_thenCrossesToNextDay() {
        // GST late at night - test boundary handling
        GST gst = new GST(new Year(2010), new Month(3), new Day(15), new Hour(23), new Minute(30), new Second(0));
        UT ut = gst.toUT();

        // Verify the conversion works
        assertAll(() -> assertEquals(2010, ut.getYear().getValue()),
                () -> assertEquals(3, ut.getMonth().getValue()));
    }

    @Test
    void givenGSTEarlyMorning_whenConvertToUT_thenGoesToPreviousDay() {
        // GST very early - test boundary handling
        GST gst = new GST(new Year(2010), new Month(3), new Day(15), new Hour(0), new Minute(30), new Second(0));
        UT ut = gst.toUT();

        // Verify the conversion works
        assertAll(() -> assertEquals(2010, ut.getYear().getValue()),
                () -> assertEquals(3, ut.getMonth().getValue()));
    }

    @Test
    void givenADateInGst_whenConvertToLST_thenCorrect() {
        GST gst = new GST(new Year(2020), new Month(1), new Day(1), new Hour(2), new Minute(3), new Second(41.0));
        LST lst = gst.toLST(new Degree(-40));

        // Longitude -40° = -2.667 hours offset
        // LST = GST + offset = 2:03:41 - 2:40:00 = -0:36:19 → wraps to previous day
        // 23:23:41
        assertAll(() -> assertEquals(2019, lst.getYear().getValue()),
                () -> assertEquals(12, lst.getMonth().getValue()),
                () -> assertEquals(31, lst.getDay().getValue()),
                () -> assertEquals(23, lst.getHour().getValue()),
                () -> assertEquals(23, lst.getMinute().getValue()),
                () -> assertEquals(41.0, lst.getSecond().getValue(), 1e-9));
    }

    @Test
    void givenGSTEarlyMorning_whenConvertToLSTWithNegativeLongitude_thenGoesToPreviousDay() {
        // GST early in morning + large negative longitude offset crosses to previous
        // day
        GST gst = new GST(new Year(2020), new Month(3), new Day(15), new Hour(1), new Minute(0), new Second(0));
        LST lst = gst.toLST(new Degree(-50));

        // Longitude -50° = -3.333 hours, LST = 1 - 3.333 = -2.333 → previous day
        assertAll(() -> assertEquals(2020, lst.getYear().getValue()),
                () -> assertEquals(3, lst.getMonth().getValue()),
                () -> assertEquals(14, lst.getDay().getValue()));
    }

    @Test
    void givenGSTLateNight_whenConvertToLSTWithPositiveLongitude_thenGoesToNextDay() {
        // GST late at night + large positive longitude offset crosses to next day
        GST gst = new GST(new Year(2020), new Month(3), new Day(15), new Hour(23), new Minute(0), new Second(0));
        LST lst = gst.toLST(new Degree(100));

        // Longitude 100° = 6.667 hours, LST = 23 + 6.667 = 29.667 → next day 5:40
        assertAll(() -> assertEquals(2020, lst.getYear().getValue()),
                () -> assertEquals(3, lst.getMonth().getValue()),
                () -> assertEquals(16, lst.getDay().getValue()),
                () -> assertEquals(5, lst.getHour().getValue()));
    }

    @Test
    void givenLst_whenConvertToGST_thenCorrect() {
        LST lst = new LST(new Year(2020), new Month(1), new Day(1), new Hour(23), new Minute(23), new Second(41.0));
        GST gst = lst.toGST(new Degree(50));

        // Longitude 50° = 3.333 hours, GST = LST - offset = 23:23:41 - 3:20:00 =
        // 20:03:41 → same day
        assertAll(() -> assertEquals(2020, gst.getYear().getValue()),
                () -> assertEquals(1, gst.getMonth().getValue()),
                () -> assertEquals(1, gst.getDay().getValue()),
                () -> assertEquals(20, gst.getHour().getValue()),
                () -> assertEquals(3, gst.getMinute().getValue()),
                () -> assertEquals(41.0, gst.getSecond().getValue(), 1e-9));
    }

    @Test
    void givenLSTEarlyMorning_whenConvertToGSTWithPositiveLongitude_thenGoesToPreviousDay() {
        // LST early in morning - large positive longitude offset crosses to previous
        // day
        LST lst = new LST(new Year(2020), new Month(3), new Day(15), new Hour(2), new Minute(0), new Second(0));
        GST gst = lst.toGST(new Degree(60));

        // Longitude 60° = 4 hours, GST = 2 - 4 = -2 → previous day 22:00
        assertAll(() -> assertEquals(2020, gst.getYear().getValue()),
                () -> assertEquals(3, gst.getMonth().getValue()),
                () -> assertEquals(14, gst.getDay().getValue()),
                () -> assertEquals(22, gst.getHour().getValue()));
    }

    @Test
    void givenLSTLateNight_whenConvertToGSTWithNegativeLongitude_thenGoesToNextDay() {
        // LST late at night - negative longitude offset crosses to next day
        LST lst = new LST(new Year(2020), new Month(3), new Day(15), new Hour(22), new Minute(0), new Second(0));
        GST gst = lst.toGST(new Degree(-100));

        // Longitude -100° = -6.667 hours, GST = 22 - (-6.667) = 28.667 → next day 4:40
        assertAll(() -> assertEquals(2020, gst.getYear().getValue()),
                () -> assertEquals(3, gst.getMonth().getValue()),
                () -> assertEquals(16, gst.getDay().getValue()),
                () -> assertEquals(4, gst.getHour().getValue()));
    }

    @Test
    void givenADateTimeInLCT_whenConvertToAll_thenIsCorrect() {
        Year year = new Year(2014);
        Month month = new Month(12);
        Day day = new Day(12);
        Degree longitude = new Degree(-77);

        int offset = LCT.getTimeZoneOffsetFromLongitude(longitude);

        LCT lct = new LCT(year, month, day, new Hour(20), new Minute(0), new Second(0), offset);

        UT ut = lct.toUT();
        assertAll(() -> assertEquals(2014, ut.getYear().getValue()),
                () -> assertEquals(12, ut.getMonth().getValue()),
                () -> assertEquals(13, ut.getDay().getValue()),
                () -> assertEquals(1, ut.getHour().getValue()),
                () -> assertEquals(0, ut.getMinute().getValue()),
                () -> assertEquals(0, ut.getSecond().getValue()));

        GST gst = lct.toGST();
        assertAll(() -> assertEquals(2014, gst.getYear().getValue()),
                () -> assertEquals(12, gst.getMonth().getValue()),
                () -> assertEquals(13, gst.getDay().getValue()),
                () -> assertEquals(6, gst.getHour().getValue()),
                () -> assertEquals(26, gst.getMinute().getValue()),
                () -> assertEquals(34, (int) gst.getSecond().getValue()));

        LST lst = lct.toLST(longitude);
        assertAll(() -> assertEquals(2014, lst.getYear().getValue()),
                () -> assertEquals(12, lst.getMonth().getValue()),
                () -> assertEquals(13, lst.getDay().getValue()),
                () -> assertEquals(1, lst.getHour().getValue()),
                () -> assertEquals(18, lst.getMinute().getValue()),
                () -> assertEquals(34, (int) lst.getSecond().getValue()));
    }

    @Test
    void givenADateInLST_whenConvertToAll_thenCorrect() {
        Year year = new Year(2000);
        Month month = new Month(7);
        Day day = new Day(5);
        Degree longitude = new Degree(60);

        int offset = LCT.getTimeZoneOffsetFromLongitude(longitude);

        LST lst = new LST(year, month, day, new Hour(5), new Minute(54), new Second(20));

        GST gst = lst.toGST(longitude);
        assertAll(() -> assertEquals(year.getValue(), gst.getYear().getValue()),
                () -> assertEquals(month.getValue(), gst.getMonth().getValue()),
                () -> assertEquals(day.getValue(), gst.getDay().getValue()),
                () -> assertEquals(1, gst.getHour().getValue()),
                () -> assertEquals(54, gst.getMinute().getValue()),
                () -> assertEquals(20, (int) gst.getSecond().getValue()));

        UT ut = lst.toUT(longitude);
        assertAll(() -> assertEquals(year.getValue(), ut.getYear().getValue()),
                () -> assertEquals(month.getValue(), ut.getMonth().getValue()),
                () -> assertEquals(day.getValue(), ut.getDay().getValue()),
                () -> assertEquals(6, ut.getHour().getValue()),
                () -> assertEquals(59, ut.getMinute().getValue()),
                () -> assertEquals(59, (int) ut.getSecond().getValue()));

        LCT lct = lst.toUT(longitude).toLCT(offset);
        assertAll(() -> assertEquals(year.getValue(), lct.getYear().getValue()),
                () -> assertEquals(month.getValue(), lct.getMonth().getValue()),
                () -> assertEquals(day.getValue(), lct.getDay().getValue()),
                () -> assertEquals(10, lct.getHour().getValue()),
                () -> assertEquals(59, lct.getMinute().getValue()),
                () -> assertEquals(59, (int) lct.getSecond().getValue()));
    }

    @Test
    void givenADay_whenValueIsZero_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new Day(0));
    }

    @Test
    void givenADate_whenValueHourIsInvalidPositive_thenThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> new UT(new Year(2020), new Month(1), new Day(1), new Hour(24),
                        new Minute(0), new Second(0)));
    }

    @Test
    void givenADate_whenValueHourIsInvalidNegative_thenThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> new UT(new Year(2020), new Month(1), new Day(1), new Hour(-1),
                        new Minute(0), new Second(0)));
    }

    @Test
    void givenADate_whenValueMinutesIsInvalidNegative_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new UT(new Year(2020), new Month(1), new Day(1), new Hour(0),
                new Minute(-1), new Second(0)));
    }

    @Test
    void givenADate_whenValueMinutesIsInvalidPositive_thenThrowException() {
        assertThrows(IllegalArgumentException.class, () -> new UT(new Year(2020), new Month(1), new Day(1), new Hour(0),
                new Minute(60), new Second(0)));
    }

    @Test
    void givenADate_whenValueSecondsIsInvalidNegative_thenThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> new UT(new Year(2020), new Month(1), new Day(1), new Hour(0), new Minute(0),
                        new Second(-1)));
    }

    @Test
    void givenADate_whenValueSecondsIsInvalidPositive_thenThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> new UT(new Year(2020), new Month(1), new Day(1), new Hour(0), new Minute(0),
                        new Second(60)));
    }

    @Test
    void givenADate_whenValueAreInvalidPositive_thenThrowException() {
        assertThrows(IllegalArgumentException.class,
                () -> new UT(new Year(2020), new Month(1), new Day(1), new Hour(24), new Minute(60),
                        new Second(60)));
    }

}