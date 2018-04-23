/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.example.entity;

import eu.discoveri.astroninja.entity.AbstractEntity;
import eu.discoveri.astroninja.exception.InvalidISOCodeLengthException;
import javax.jdo.annotations.Index;
import javax.persistence.Entity;


/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
@Entity
public class Languages extends AbstractEntity
{
    @Index(unique="true")
    private String  codeISO6391;  // 2 letter code
    private String  name,
                    nativeName,
                    note;

    /**
     * No-arg constructor
     */
    public Languages(){}

    /**
     * Constructor.
     * 
     * @param codeISO6391 Two letter code, eg: ab, en, la
     * @param name eg: Abkhaz, English, Latin
     * @param nativeName аҧсшәа, English...
     * @param note eg: Ancient language
     * @throws InvalidISOCodeLengthException
     */
    public Languages(String codeISO6391, String name, String nativeName, String note)
            throws InvalidISOCodeLengthException
    {
        if( codeISO6391.length() != 2 )
            throw new InvalidISOCodeLengthException("Must be two characters only");
        this.codeISO6391 = codeISO6391;
        
        this.name = name;
        this.nativeName = nativeName;
        this.note = note;
    }

    
    /**
     * Get ISO639-1 (2 letter code)
     * @return 
     */
    public String getCodeISO6391() { return codeISO6391; }

    /**
     * Set language code (ISO639-1)
     * @param codeISO6391 
     * @throws InvalidISOCodeLengthException
     */
    public void setCodeISO6391(String codeISO6391)
            throws InvalidISOCodeLengthException
    {
        if( codeISO6391.length() != 2 )
            throw new InvalidISOCodeLengthException("Must be two characters only");
        this.codeISO6391 = codeISO6391;
    }

    /**
     * Get name
     * @return 
     */
    public String getName() { return name; }

    /**
     * Set name
     * @param name 
     */
    public void setName(String name) { this.name = name; }

    /**
     * Get native name
     * @return 
     */
    public String getNativeName() { return nativeName; }

    /**
     * Set native name
     * @param nativeName 
     */
    public void setNativeName(String nativeName) { this.nativeName = nativeName; }

    /**
     * Get note
     * @return 
     */
    public String getNote() { return note; }

    /**
     * Set note
     * @param note 
     */
    public void setNote(String note) { this.note = note; }
}
