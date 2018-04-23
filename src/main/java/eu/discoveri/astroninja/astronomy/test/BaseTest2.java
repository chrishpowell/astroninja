/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.astronomy.test;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.JulianFields;


/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class BaseTest2
{
    public static void main(String[] args)
    {
        LocalDate locdate = LocalDate.of(1953, Month.MARCH, 7);
        System.out.println("Julian Day: " +JulianFields.JULIAN_DAY.getFrom(locdate));
        System.out.println("Modified Julian Day: " +JulianFields.MODIFIED_JULIAN_DAY.getFrom(locdate));
    }
}
