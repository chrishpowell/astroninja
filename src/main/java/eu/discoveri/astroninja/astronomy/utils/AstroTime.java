/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */
package eu.discoveri.astroninja.astronomy.utils;

import eu.discoveri.astroninja.exception.HoursOutOfRangeException;
import static   eu.discoveri.astroninja.astronomy.utils.AstroConst.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;


/**
 * This is a class which defines the algorithms and calculations for astronomical time.
 * 
 * Other refs: http://www.geoastro.de/elevaz/basics/index.htm,
 * http://www.stargazing.net/kepler/altaz.html,
 * http://www2.arnes.si/~gljsentvid10/sidereal.htm
 * 
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class AstroTime
{
    /**
     * Time as decimal hours.
     * 
     * @param hours
     * @param minutes
     * @param seconds
     * @return 
     */
    public static double timeToDec(double hours, double minutes, double seconds) { return hours + (minutes / 60) + (seconds / 3600); }
    
    /**
     * Fractional hours as LocalTime.
     * Note: If hours in degrees, divide by 15.
     * 
     * @param hours, decimal (0.0 <= hours <= 24.0)
     * @return 
     * @throws HoursOutOfRangeException
     */
    public static LocalTime timeToLocal( double hours )
            throws HoursOutOfRangeException
    {
        if( hours > 24.0 || hours < 0.0 )
            throw new HoursOutOfRangeException("Hours range 0.0 to 24.0 only");
        
        double h = Math.floor(hours);
        double m = Math.floor((hours-h)*SECSMINSHRS);
        double s = Math.floor(((hours-h)*SECSMINSHRS-m)*SECSMINSHRS);
        
        return LocalTime.of((int)h, (int)m, (int)s);
    }

    /**
     * Julian days (+fractions)
     * 
     * @param y
     * @param m
     * @param d
     * @param hour
     * @param minute
     * @param seconds
     * @return 
     */
    public static double getDaysJulian(double y, double m, double d, double hour, double minute, double seconds)
    {
        // Add the hour decimal.
        d += timeToDec(hour, minute, seconds) / 24d;

        // If we are calculating for january or february, consider it the "13th and 14th" months.
        if (m == 1d || m == 2d)
        {
            y = y - 1d;
            m = m + 12d;
        }

        double A = (int)(y / 100d);
        double B = 2d - A + (int)(A / 4d);
        return (int)(DAYSINYR * (y + 4716d)) + (int)(30.6001d * (m + 1d)) + d + B - 1524.5d;
    }
    
    /**
     * Julian days (+fraction).
     * 
     * @param locDT
     * @return 
     */
    public static double getDaysJulian( LocalDateTime locDT )
    {
        return getDaysJulian( locDT.getYear(), locDT.getMonthValue(), locDT.getDayOfMonth(),
                              locDT.getHour(), locDT.getMinute(), locDT.getSecond() );
    }

    /**
     * Local Sidereal Time (as double).  See Meeus, Algorithms
     * 
     * @param utYear
     * @param utMonth
     * @param utDay
     * @param utHour
     * @param utMinute
     * @param utSeconds
     * @param longitude
     * @return 
     */
    public static double getLocalSiderealTime(int utYear, int utMonth, int utDay, int utHour, int utMinute, int utSeconds, double longitude)
    {
        // Julian days
        double JD = getDaysJulian(utYear, utMonth, utDay, utHour, utMinute, utSeconds);
        double T = (JD - JD1JAN2000) / DAYSINCTY;

        // Mean sidereal time (Theta0, in degrees) at Greenwich for an instant.  See Meeus, Algorithms
        // SIDTIME (LMST) = GMST0 + UT + LON/15 (approx.)
        double LMST = 280.46061837d + 360.98564736629d * (JD - JD1JAN2000)
                + 0.000387933d * Math.pow(T, 2) - (Math.pow(T, 3) / 38710000d);

        // Adjust the location (which is already represented in a degree decimal).
        LMST += longitude;

        // Bring it within range.
        double range = DEGREES360;
        while (LMST < 0d || LMST > range)
        {
            if ( LMST > range) LMST -= range;
            else LMST += range;
        }

        // Now we have the Local Mean Sidereal Time
        return LMST;
    }
    
    /**
     * Local Sidereal Time (as double)
     * 
     * @param locDT
     * @param longitude
     * @return 
     */
    public static double getLocalSiderealTime( LocalDateTime locDT, double longitude )
    {
        return getLocalSiderealTime( locDT.getYear(), locDT.getMonthValue(), locDT.getDayOfMonth(),
                                     locDT.getHour(), locDT.getMinute(), locDT.getSecond(), longitude );
    }

    /**
     * Hour Angle.
     * 
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param seconds
     * @param longitude
     * @param rightAscension
     * @return 
     */
    public static double getHourAngle(int year, int month, int day, int hour, int minute, int seconds, double longitude, double rightAscension)
    {
        double result = getLocalSiderealTime(year, month, day, hour, minute, seconds, longitude) - rightAscension;
        if (result < 0)
        {
            result += DEGREES360;
        }

        return result;
    }
    
    /**
     * Hour Angle.
     * 
     * @param locDT
     * @param longitude
     * @param rightAscension
     * @return 
     */
    public static double getHourAngle( LocalDateTime locDT, double longitude, double rightAscension )
    {
        return getHourAngle(locDT.getYear(),
                            locDT.getMonthValue(),
                            locDT.getDayOfMonth(),
                            locDT.getHour(),
                            locDT.getMinute(),
                            locDT.getSecond(),
                            longitude,
                            rightAscension  );
    }

    /**
     * Current Sidereal Time
     * 
     * @param longitude
     * @return 
     */
    public static double getCurrentSiderealTime(double longitude)
    {
        ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
        return getLocalSiderealTime(utc.getYear(), utc.getMonthValue(), utc.getDayOfMonth(), utc.getHour(), utc.getMinute(), utc.getSecond(), longitude);
    }
}
