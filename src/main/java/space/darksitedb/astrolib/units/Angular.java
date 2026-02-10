package space.darksitedb.astrolib.units;

/**
 * Interface representing an angular unit. It provides methods to convert the angular measurement to different units.
 */
public interface Angular {

    Radian toRadians();

    Degree toDegrees();

    Hour toHours();

    Dms toDms();

}
