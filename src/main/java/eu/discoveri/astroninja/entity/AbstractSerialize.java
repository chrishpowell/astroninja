/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.entity;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;


/**
 * AbstractSerialize for oodb embedded.
 * 
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
@Inheritance
public class AbstractSerialize implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue
    private long id;

    public long getId() { return id; }
}
