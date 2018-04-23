/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.astronomy.test;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;


/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class BaseTest3
{
    public static void main(String[] args)
    {
        Clock clock = Clock.systemDefaultZone();
        System.out.println("Curr def. time millisecs: " +clock.millis()+ ", Readable: " +clock.instant());
        
        System.out.println("TZ def.: " +TimeZone.getDefault());
        
        clock = Clock.system(ZoneId.of("Europe/Podgorica"));
        System.out.println("Curr. time loc. ??: " +clock.instant());
        
        System.out.println("Curr. time loc. : " +LocalDateTime.now());
        
        clock = Clock.systemUTC();
        System.out.println("Curr UTC: " +clock.instant());
    }
}
