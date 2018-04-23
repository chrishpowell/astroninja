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
public class RAdec
{
    @JsonProperty("dec")
    private DegreeString decl;
    @JsonProperty("ra")
    private DegreeString ra;
    
    
    public DegreeString getDecl() { return decl; }
    public void setDecl(DegreeString decl) { this.decl = decl; }
    
    public DegreeString getRA() { return ra; }
    public void setRA(DegreeString ra) { this.ra = ra; }
}
