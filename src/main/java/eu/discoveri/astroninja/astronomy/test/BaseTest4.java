/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.astronomy.test;

import eu.discoveri.astroninja.astronomy.utils.AstroTime;
import java.time.LocalDateTime;

/**
 * Some sidereal time testing.  Check against: http://www.jgiesen.de/astro/astroJS/siderealClock/
 * 
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class BaseTest4
{
    public static void main(String[] args)
            throws Exception
    {
        LocalDateTime locDT = LocalDateTime.now();
//        double jd = AstroTime.getDaysJulian(locDT.getYear(), locDT.getMonthValue(), locDT.getDayOfMonth(),
//                                            locDT.getHour(), locDT.getMinute(), locDT.getSecond());
//        double lst = AstroTime.getLocalSiderealTime(locDT.getYear(), locDT.getMonthValue(), locDT.getDayOfMonth(),
//                                                    locDT.getHour(), locDT.getMinute(), locDT.getSecond(), 19.2594);
        double jd = AstroTime.getDaysJulian( locDT );
        double lst = AstroTime.getLocalSiderealTime( locDT, 19.2594 );
        
        System.out.println("Julian Day: " +jd);
        System.out.println("AstroTime LMST: " +lst );
        double cst = AstroTime.getCurrentSiderealTime(19.2594);
        System.out.println("AstroTime CLST: " +cst);
        System.out.println("CST as time...: " +AstroTime.timeToLocal(cst/15.));
    }
}
