/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.location;

import eu.discoveri.astroninja.entity.AbstractSerialize;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;


/**
 * Location is peculiar to a user.  It's true that a location may be diffuse but
 * to match users to Locations given the difficulty of a complete location match
 * especially where imprecise coords are provided.  A geohash match would be
 * required and the probability that any two users are in the same hash (unless
 * its a large area) is small.  Consequently Location is not an Entity.
 * 
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
@Embeddable
public class Location extends AbstractSerialize
{
    public enum Hemisphere {NORTH,SOUTH};
    public enum Continent
    {
        AFRICA("Africa"),
        ANTARCTICA("Antarctica"),
        ASIA("Asia"),
        AUSTRALIA("Australia"),
        EUROPE("Europe"),
        NAMERICA("North America"),
        SAMERICA("South America");
        
        private final String name;
        
        Continent( String name ) { this.name = name; }
        
        public String getName() { return name; }
    }
    
    private Hemisphere  hemisphere;
    private Continent   continent;
    private Country     country;        // Also holds district/region list
    private String      subRegion;
    private String      townCity;
    @Embedded
    private Coordinates coords;

    /*
     * No-arg constructor
     */
    protected Location(){}

    /**
     * Constructor. Note: May not have coords, just a city or region.
     * 
     * @param hemisphere
     * @param continent
     * @param subRegion
     * @param country
     * @param townCity
     * @param coords 
     */
    public Location(Hemisphere hemisphere, Continent continent, String subRegion, Country country, String townCity, Coordinates coords)
    {
        this.hemisphere = hemisphere;
        this.continent = continent;
        this.country = country;
        this.townCity = townCity;
        this.coords = coords;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getTownCity() {
        return townCity;
    }

    public void setTownCity(String townCity) {
        this.townCity = townCity;
    }

    public Coordinates getCoords() {
        return coords;
    }

    public void setCoords(Coordinates coords) {
        this.coords = coords;
    }
    
    // Match Locations (where possible)
    public boolean matchLocation( Location other )
    {
        return this.equals(other);
    }
}
