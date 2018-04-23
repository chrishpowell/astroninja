/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.test.throwaway;

import eu.discoveri.astroninja.entity.AbstractEntity;
import javax.persistence.Embedded;
import javax.persistence.Entity;


/**
 * @author Chris Powell, Discoveri OU
 */
@Entity
public class Useren extends AbstractEntity
{
    @Embedded
    private Name name;
    private String email;

    public Useren() {}
    public Useren(Name name, String email )
    {
        this.name = name;
        this.email = email;
    }

    public Name getName() { return name; }
    public String getEmail() { return email; }
}
