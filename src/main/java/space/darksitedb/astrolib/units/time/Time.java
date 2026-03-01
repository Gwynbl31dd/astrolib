package space.darksitedb.astrolib.units.time;

import space.darksitedb.astrolib.units.angle.Degree;

/**
 * Interface representing a time unit. It provides methods to convert the time measurement to different units.
 * 
 * What is time? Nobody knows.
 */
public interface Time {

    Degree toDegrees();

    Hour toDecimalHours();

    Hms toHms();

}
