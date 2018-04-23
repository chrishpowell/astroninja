/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.aapi;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class Positions
{
    private AltAz           altaz;
    @JsonProperty("const")
    private Constellation   cons;
    private RAdec           radec;

    
    public AltAz getAltaz() { return altaz; }
    public void setAltaz(AltAz altaz) { this.altaz = altaz; }

    public Constellation getCons() { return cons; }
    public void setCons(Constellation cons) { this.cons = cons; }

    public RAdec getRadec() { return radec; }
    public void setRadec(RAdec radec) { this.radec = radec; }
}
