/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.example.entity;

import eu.discoveri.astroninja.entity.AbstractEntity;
import javax.jdo.annotations.Index;
import javax.persistence.Column;


/**
 * Solar system bodies.  Includes translations.
 * 
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class Body extends AbstractEntity
{
    @Index(unique="true")
    private String  name;       // Eg: Ceres
    private String  description,
                    symbol;
    @Column(name="secName",nullable=true)
    @Index(unique="true") //  Null handling??
    private String  secName;    // Secondary name, eg: Jupiter XVII

    /**
     * Constructor.
     * 
     * @param name
     * @param description
     * @param symbol 
     * @param secName The secondary name for an body (eg: Jupiter XVII)
     */
    public Body(String name, String description, String symbol, String secName)
    {
        this.name = name;
        this.description = description;
        this.symbol = symbol;
        this.secName = secName;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getSymbol() { return symbol; }

    public void setSymbol(String symbol) { this.symbol = symbol; }

    public String getSecName() { return secName; }

    public void setSecName(String secName) { this.secName = secName; }
}
