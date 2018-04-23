/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */
package eu.discoveri.astroninja.astronomy.test;

import eu.discoveri.astroninja.astronomy.projection.PositionSystem;
import eu.discoveri.astroninja.astronomy.utils.AstroCoords;
import eu.discoveri.astroninja.astronomy.utils.AstroTime;
import java.time.LocalDateTime;
import java.time.temporal.JulianFields;


/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class BaseTest1
{
    // Here is an example to show that the calculations work. The example is a random one
    // I found online:
    //
    // http://www.stargazing.net/kepler/altaz.html
    //
    // Although they show you the step by step way to calculate by hand, you can
    // verify this program is outputting the same values.
    public static void main(String[] args)
    {
        double rightAscension = Math.toRadians(AstroCoords.rightAscensionToDegrees(16, 41.7, 0));
        double declination = Math.toRadians(AstroCoords.declinationToDegrees(36, 28, 0));
        double latitude = Math.toRadians(AstroCoords.declinationToDegrees(52, 30, 0));
        double longitude = Math.toRadians(AstroCoords.declinationToDegrees(1, 55, 0) * -1);

        int year = 1998;
        int month = 8;
        int day = 10;
        int hour = 23;
        int minute = 10;
        int seconds = 0;

        double LST = Math.toRadians(AstroTime.getLocalSiderealTime(year, month, day, hour, minute, seconds, Math.toDegrees(longitude)));
        double HA = Math.toRadians(AstroTime.getHourAngle(year, month, day, hour, minute, seconds, Math.toDegrees(longitude), Math.toDegrees(rightAscension)));

        double sinAlt = Math.sin(declination) * Math.sin(latitude) + Math.cos(declination) * Math.cos(latitude) * Math.cos(HA);
        double altitude = Math.asin(sinAlt);

        double cosA =
                (Math.sin(declination) - (sinAlt * Math.sin(latitude)))
                        /
                        (Math.cos(altitude) * Math.cos(latitude));

        double AZ = Math.acos(cosA);
        if (Math.sin(HA) >= 0 )
            AZ = (2 * Math.PI) - AZ;

        PositionSystem tracker = new PositionSystem(47.6062, -122.33, 0, 0);
        tracker.track(AstroCoords.rightAscensionToDegrees(16, 41, 41.45), AstroCoords.declinationToDegrees(36, 27, 36.9));
        System.out.println("Altitude: " + tracker.getAltitude() + "   Azimuth: " + tracker.getAzimuth());

        System.out.println("=== AZ / ALT EXAMPLE ===");
        System.out.println("DEC: " + Math.toDegrees(declination));
        System.out.println("LAT: " + Math.toDegrees(latitude));
        System.out.println("LST: " + Math.toDegrees(LST));
        System.out.println("HA: " + Math.toDegrees(HA));
        System.out.println("Altitude: " + Math.toDegrees(altitude) + "   Azimuth: " + Math.toDegrees(AZ));

        System.out.println("\n=== Julian Days ===");
        System.out.println("AstroTime: " +AstroTime.getDaysJulian(year,month,day,hour,minute,seconds) );
        LocalDateTime locDT = LocalDateTime.of(year, month, day, hour, minute, seconds);
        System.out.println("java.time... JD:" +JulianFields.JULIAN_DAY.getFrom(locDT)+ ", MJD: " +JulianFields.MODIFIED_JULIAN_DAY.getFrom(locDT));
        
        System.out.println("\n=== Current Sidereal Time ===");
        System.out.println("Local sidereal: " + (AstroTime.getCurrentSiderealTime(-122.33) / 15d));
    }
}
