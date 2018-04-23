/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.astronomy.projection;

/**
 * 3d Cartesian coordinates.  Might change this for Java3D project: 
 * http://www.oracle.com/technetwork/articles/javase/index-jsp-138252.html
 * 
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class CartesianCoordinates3D
{
    private double x, y, z;

    /**
     * Constructor.
     * 
     * @param x
     * @param y
     * @param z 
     */
    public CartesianCoordinates3D(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Get x coord.
     * 
     * @return 
     */
    public double getX() { return x; }

    /**
     * Get y coord.
     * 
     * @return 
     */
    public double getY() { return y; }

    /**
     * Get z coord.
     * 
     * @return 
     */
    public double getZ() { return z; }
    
    /**
     * Set location.
     * 
     * @param x
     * @param y
     * @param z 
     */
    public void setLocation( double x, double y, double z )
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    /**
     * Quick output.
     * 
     * @return 
     */
    @Override
    public String toString()
    {
        return "x: " +x+ ", y: " +y+ ", z: " +z;
    }
}
