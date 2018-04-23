/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.example.entity;

import eu.discoveri.astroninja.entity.AbstractEntity;
import eu.discoveri.astroninja.location.Location;
import javax.persistence.Embedded;
import javax.persistence.Entity;


/**
 * @author Chris Powell, Discoveri OU
 */
@Entity
public class AUser extends AbstractEntity
{
    private String      name;
    private String      email;
    @Embedded
    private Location    locn;

    public AUser() {}
    public AUser(String name, String email, Location locn )
    {
        this.name = name;
        this.email = email;
        this.locn = locn;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public Location getLocation() { return locn; }
}
