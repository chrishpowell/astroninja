/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */
package eu.discoveri.astroninja.astronomy.projection;

import javafx.geometry.Point2D;

/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class Celestial2DProjection
{
    /**
     * All fields in degrees.  See Wolfram: http://mathworld.wolfram.com/GnomonicProjection.html
     * 
     * @param ra0  RA at centre
     * @param decl0  Declination at centre
     * @param x0  Cartesian centre of map, x
     * @param y0  Cartesian centre of map, y
     * @param projRadius  Radius of projection in mm
     * @param rightAsc  RA of star
     * @param declination  Declination of star
     * @return 
     */
    public static Point2D EquatorialToCartesian( double ra0, double decl0,
                                                 double x0, double y0,
                                                 double projRadius, 
                                                 double rightAsc, double declination )
    {
        // Degrees to radians
        double netRA = Math.toRadians(rightAsc-ra0);
        double decl = Math.toRadians(declination);
        double d0 = Math.toRadians(decl0);
        
        // Calculate direction vector
        double p = Math.sin(decl);
        double q = Math.cos(decl)*Math.sin(netRA);
        double r = Math.cos(decl)*Math.cos(netRA);
        
        // Offset: decl0 is the declination of mapped centre
        double s = p * Math.sin(d0) + r * Math.cos(d0);
        // Standard coords
        double x = -q/s;  // Neg. q?
        double y = (p*Math.cos(d0) - r*Math.sin(d0))/s;
        
        // Project
        return new Point2D(x*projRadius,y*projRadius);
    }
    
    public static Point2D EquatorialToCartesian1( double ra0, double decl0,
                                                 double x0, double y0,
                                                 double projRadius, 
                                                 double rightAsc, double declination )
    {
        // Degrees to radians
        double decl = Math.toRadians(declination);
        double ra = Math.toRadians(rightAsc);
        double d0 = Math.toRadians(decl0);
        
        // Equatorial Streographic Projection
        double xprime = Math.cos(decl) * Math.sin(ra);
        double yprime = Math.sin(decl);
        double zprime = Math.cos(decl) * Math.cos(ra);
                
                
        double zfactor = 1.0/(1.0 + zprime);
        
        return new Point2D(xprime*zfactor,yprime*zfactor);
    }
    
    public static Point2D EquatorialToCartesian3(double ra0, double decl0,
                                                 double x0, double y0,
                                                 double projRadius, 
                                                 double rightAsc, double declination)
    {
        // Degrees to radians
        double decl = Math.toRadians(declination);
        double ra = Math.toRadians(rightAsc);
        double r0 = Math.toRadians(ra0);
        double d0 = Math.toRadians(decl0);
        
        // Projection
        double xprime = Math.cos(decl) * Math.cos(d0) * Math.cos(ra-r0) + Math.sin(decl) * Math.sin(d0);
        double yprime = Math.sin(ra-r0) * Math.cos(decl);
        double zprime = -Math.sin(d0) * Math.cos(ra-r0) * Math.cos(decl) + Math.cos(d0) * Math.sin(decl);
        
        return new Point2D(-yprime/zprime,xprime/zprime);
    }
}
