/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.example.entity;

/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class MinorBody extends Body
{
    private enum BodyType {MOON,ASTEROID,COMET};
    private BodyType bodyType;

    /**
     * Constructor.
     * 
     * @param name
     * @param description
     * @param symbol
     * @param secName
     * @param bodyType 
     */
    public MinorBody(String name, String description, String symbol, String secName, BodyType bodyType)
    {
        super(name, description, symbol, secName);
        this.bodyType = bodyType;
    }

    /**
     * Get BodyType
     * @return 
     */
    public BodyType getBodyType() { return bodyType; }

    /**
     * Set body type
     * @param bodyType 
     */
    public void setBodyType(BodyType bodyType)
    {
        this.bodyType = bodyType;
    }
}
