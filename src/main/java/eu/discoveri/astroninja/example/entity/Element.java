/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */
package eu.discoveri.astroninja.example.entity;

/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public enum Element
{
    EARTH("Earth"),
    WATER("Water"),
    FIRE("Fire"),
    AIR("Air");
    
    private final String description;
    
    Element(String description)
    {
        this.description = description;
    }
    
    public String getDescription()
    {
        return description;
    }
}
