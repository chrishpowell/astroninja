/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */
package eu.discoveri.astroninja.astronomy;

/**
 *
 * @author Chris Powell, Discoveri OU
 */
public interface ConstlStar
{
    /**
     * Some form of Id, eg: row number, embedded ID etc.
     * @return 
     */
    public String getId();
    
    /**
     * Get a Proper name (eg: "Rigel")
     * @return 
     */
    public String getProperName();
    
    /**
     * Get a substitute name (eg: "64Gam UMa" or Bayer-Flamsteed name etc.)
     * @return 
     */
    public String getSubsName();
    
    /**
     * Get a name that works (if ProperName and/or SubsName null, for example.
     * @return A displayable name
     */
    public String getDisplayName();
    
    /**
     * Get Right Asc. (hours,mins,secs as float).
     * @return float
     */
    public float getRA();
    
    /**
     * Get Declination (float)
     * @return 
     */
    public float getDecl();
    
    /**
     * Get distance in parsecs (float)
     * @return 
     */
    public float getDistParsecs();
    
    /**
     * Get Magnitude.
     * @return 
     */
    public float getMagnitude();

    /**
     * Get containing constellation name (if any)
     * @return 
     */
    public String getConstellation();
    
    /**
     * Projection
     * @return 
     */
    public java.awt.geom.Point2D.Double getProjection();
    public javafx.geometry.Point2D getProjection2();
    public javafx.geometry.Point2D getProjection3();
    public javafx.geometry.Point2D getProjection4();
    
    /**
     * Set the projection X coord
     * @param xproj 
     */
    public void setXproj(double xproj);
    /**
     * Set the projection Y coord
     * @param yproj 
     */
    public void setYproj(double yproj);
}
