/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.astronomy.utils;

import static eu.discoveri.astroninja.astronomy.utils.AstroTime.timeToDec;

/**
 * This is a class which defines the algorithms and calculations for astronomical coords.
 * 
 * Other refs: http://www.geoastro.de/elevaz/basics/index.htm,
 * http://www.stargazing.net/kepler/altaz.html,
 * http://www2.arnes.si/~gljsentvid10/sidereal.htm
 * 
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class AstroCoords
{

    /**
     * RA to degrees: 15 degrees per hour
     * 
     * @param hours
     * @param minutes
     * @param seconds
     * @return 
     */
    public static double rightAscensionToDegrees(double hours, double minutes, double seconds) { return timeToDec(hours, minutes, seconds) * 15; }

    /**
     * Decl to degrees.
     * 
     * @param degrees
     * @param minutes
     * @param seconds
     * @return 
     */
    public static double declinationToDegrees(double degrees, double minutes, double seconds) { return degrees + timeToDec(0 , minutes, seconds); }

    /**
     * Coords to degrees.
     * 
     * @param degrees
     * @param minutes
     * @return 
     */
    public static double coordToDec(double degrees, double minutes) { return degrees + timeToDec(0, minutes, 0); }
}
