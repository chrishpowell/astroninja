/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.aapi;

/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class Constellation
{
    private String  abbr;
    private String  id;
    private String  name;

    
    public String getAbbr() { return abbr; }
    public void setAbbr(String abbr) { this.abbr = abbr; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
