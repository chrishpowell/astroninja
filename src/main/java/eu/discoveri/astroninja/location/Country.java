/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.location;

import eu.discoveri.astroninja.entity.AbstractEntity;
import eu.discoveri.astroninja.exception.InvalidISOCodeLengthException;

import java.util.Set;
import javax.jdo.annotations.Index;
import javax.persistence.Entity;


/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
@Entity
public class Country extends AbstractEntity
{
    @Index(unique="true")
    private String              ctryISO31661;   // 2 char code
    private Set<CtrySubDiv>     subDivs;        // Eg: AF-BDS => Afghanistan:Badakshan
    private String              numericCode,
                                name;

    protected Country(){}
    
    public Country(String ctryISO31661, Set<CtrySubDiv> subDivs, String numericCode, String name)
            throws InvalidISOCodeLengthException
    {
        setCtryISO31661(ctryISO31661);
        this.subDivs = subDivs;
        this.numericCode = numericCode;
        this.name = name;
    }

    public String getCtryISO31661() {
        return ctryISO31661;
    }

    public final void setCtryISO31661(String ctryISO31661)
            throws InvalidISOCodeLengthException
    {
        if( ctryISO31661.length() != 2 )
            throw new InvalidISOCodeLengthException("Must be two characters only");
        this.ctryISO31661 = ctryISO31661;
    }

    public Set<CtrySubDiv> getSubDiv() {
        return subDivs;
    }

    public void setSubDiv(Set<CtrySubDiv> subDivs) {
        this.subDivs = subDivs;
    }

    public String getNumericCode() {
        return numericCode;
    }

    public void setNumericCode(String numericCode) {
        this.numericCode = numericCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
