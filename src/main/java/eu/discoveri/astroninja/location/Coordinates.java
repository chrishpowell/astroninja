/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.location;

import ch.hsr.geohash.GeoHash;
import eu.discoveri.astroninja.entity.AbstractSerialize;
import javax.persistence.Embeddable;


/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
@Embeddable
public class Coordinates extends AbstractSerialize
{
    private double  lat, lon;
    private String geoHash;

    /*
     * No-arg constructor
     */
    protected Coordinates(){}
    
    /**
     * Constructor.  Generates GeoHash to 64 bits precision.
     * 
     * @param lat
     * @param lon 
     */
    public Coordinates(double lat, double lon)
    {
        this.lat = lat;
        this.lon = lon;
        geoHash = GeoHash.withBitPrecision(lat, lon, 64).toBinaryString();
    }

    /**
     * GeoHash alone produces a BoundingBox (minLat:maxLat etc.) for lat/lon,
     * hence lat/lon set to zero here.
     * 
     * @param geoHash 
     */
    public Coordinates(GeoHash geoHash)
    {
        this.lat = 0.0d;
        this.lon = 0.0d;
        this.geoHash = geoHash.toBinaryString();
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public GeoHash getGeoHash() {
        return GeoHash.fromBinaryString(geoHash);
    }

    public void setGeoHash(GeoHash geoHash) {
        this.geoHash = geoHash.toBinaryString();
    }
}
