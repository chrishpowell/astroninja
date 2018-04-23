/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.aapi;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class Observer
{
    @JsonProperty("geo")
    private GeoLatLon   geoLatLon;

    public GeoLatLon getGeoLatLon() { return geoLatLon; }
    public void setGeoLatLon(GeoLatLon geoLatLon) { this.geoLatLon = geoLatLon; }
}
