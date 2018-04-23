/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.aapi;

/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class AltAz
{
    private DegreeString alt;
    private DegreeString az;
    
    
    public DegreeString getAlt(){ return alt; }
    public void setAlt(DegreeString alt) { this.alt = alt; }
    
    public DegreeString getAz(){ return az; }
    public void setAz(DegreeString az) { this.az = az; }
}
