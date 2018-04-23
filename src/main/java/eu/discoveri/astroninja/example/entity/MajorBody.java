/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.example.entity;

import java.util.List;

/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class MajorBody extends Body
{
    private enum BodyType {SUN, PLANET, DWARFP};
    private List<MinorBody> minorBodies;

    public MajorBody(String name, String description, String symbol, String secName)
    {
        super(name, description, symbol, secName);
    }
            
    

}