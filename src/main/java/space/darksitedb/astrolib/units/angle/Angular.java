package space.darksitedb.astrolib.units.angle;

import space.darksitedb.astrolib.units.time.Hour;

/**
 * Interface representing an angular unit. It provides methods to convert the
 * angular measurement to different units.
 */
public interface Angular {

    Radian toRadians();

    Degree toDegrees();

    Hour toHours();

    Dms toDms();

}
