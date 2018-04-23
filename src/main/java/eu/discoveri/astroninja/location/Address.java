/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.location;

import eu.discoveri.astroninja.entity.AbstractEntity;
import javax.jdo.annotations.Index;
import javax.persistence.Embedded;


/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
@Index(members={"location.country.ctryISO31661"}, unique="true")
public class Address extends AbstractEntity
{
    private String      numberName,     // Eg: '13' or '#7' or '2a' or 'Dowager Rooms' or 'Floor 2'
                        street,
                        stateDistrict,  // County or Department etc.
                        zipPostCode;
    @Embedded
    private Location    location;       // Include cityTown, country etc.

    public Address(String numberName, String street, Location location, String stateDistrict, String zipPostCode)
    {
        this.numberName = numberName;
        this.street = street;
        this.location = location;
        this.stateDistrict = stateDistrict;
        this.zipPostCode = zipPostCode;
    }

    public String getNumberName() {
        return numberName;
    }

    public void setNumberName(String numberName) {
        this.numberName = numberName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getStateDistrict() {
        return stateDistrict;
    }

    public void setStateDistrict(String stateDistrict) {
        this.stateDistrict = stateDistrict;
    }

    public String getZipPostCode() {
        return zipPostCode;
    }

    public void setZipPostCode(String zipPostCode) {
        this.zipPostCode = zipPostCode;
    }
}
