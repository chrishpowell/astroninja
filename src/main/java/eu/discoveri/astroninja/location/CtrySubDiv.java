/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.location;

import eu.discoveri.astroninja.entity.AbstractSerialize;
import eu.discoveri.astroninja.example.entity.Languages;

import java.util.Set;
import javax.persistence.Embeddable;


/**
 * https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes
 * 
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
@Embeddable
public class CtrySubDiv extends AbstractSerialize
{
    private String          subDivCode;
    private String          name;
    private Set<Languages>  langs;

    /**
     * No-arg constructor
     */
    protected CtrySubDiv(){}
    
    /**
     * Constructor.
     * 
     * @param subDivCode
     * @param name
     * @param langs 
     */
    public CtrySubDiv(String subDivCode, String name, Set<Languages> langs)
    {
        this.subDivCode = subDivCode;
        this.name = name;
        this.langs = langs;
    }

    public String getSubDivCode() {
        return subDivCode;
    }

    public void setSubDivCode(String subDivCode) {
        this.subDivCode = subDivCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Languages> getLangs() {
        return langs;
    }

    public void setLangs(Set<Languages> langs) {
        this.langs = langs;
    }
}
