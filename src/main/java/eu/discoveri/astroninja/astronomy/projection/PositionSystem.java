/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */
package eu.discoveri.astroninja.astronomy.projection;

import eu.discoveri.astroninja.astronomy.utils.AstroTime;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;


/**
 * Manage position and utilize the AstroTime library.
 * 
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class PositionSystem
{
    // Everything in degrees.
    private double latitude = 0.0;
    private double longitude = 0.0;
    private double azimuthOffset = 0.0;
    private double altitudeOffset = 0.0;
    private double azimuth = 0.0;
    private double altitude = 0.0;

    /**
     * Constructor.
     * 
     * @param latitude
     * @param longitude
     * @param azimuthOffset
     * @param altitudeOffset 
     */
    public PositionSystem(double latitude, double longitude, double azimuthOffset, double altitudeOffset)
    {
        this.latitude = latitude;
        this.longitude = longitude;
        this.azimuthOffset = azimuthOffset;
        this.altitudeOffset = altitudeOffset;
    }

    /**
     * Get Azimuth.
     * 
     * @return 
     */
    public double getAzimuth() { return azimuth + azimuthOffset; }

    /**
     * Get Altitude.
     * 
     * @return 
     */
    public double getAltitude() { return altitude + altitudeOffset; }

    /**
     * Set Altitude.
     * 
     * @param alt 
     */
    public void setAltitude(double alt) { this.altitude = alt; }

    /**
     * Set Azimuth.
     * 
     * @param az 
     */
    public void setAzimuth(double az) { this.azimuth = az; }

    /**
     * Track...?
     * 
     * @param rightAscension
     * @param declination 
     */
    public void track(double rightAscension, double declination)
    {
        ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);

        // Hour Angle
        double HA = Math.toRadians( AstroTime.getHourAngle(LocalDateTime.now(),longitude,rightAscension) );

        // Radians now.
        double d = Math.toRadians(declination);
        double lat = Math.toRadians(latitude);

        double sinAlt = Math.sin(d) * Math.sin(lat) + Math.cos(d) * Math.cos(lat) * Math.cos(HA);
        double alt = Math.asin(sinAlt);
        double cosAz = (Math.sin(d) - (sinAlt * Math.sin(lat))) / (Math.cos(altitude) * Math.cos(lat));
        double az = Math.acos(cosAz);
        if (Math.sin(HA) >= 0 ) azimuth = (2 * Math.PI) - azimuth;

        // Update the Alt./Az.
        this.altitude = Math.toDegrees(alt);
        this.azimuth = Math.toDegrees(az);
    }
}
