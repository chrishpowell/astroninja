/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.astronomy.test;

import eu.discoveri.astroninja.astronomy.projection.CoordinateConversion;
import eu.discoveri.astroninja.astronomy.projection.CartesianCoordinates3D;

/**
 * Coordinate Conversion test
 * 
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class BaseTest5
{
    public static void main(String[] args)
            throws Exception
    {
        // 
        CartesianCoordinates3D cc3d = CoordinateConversion.convertSphericalToCartesian( 0.945143, 60.71674, 168.3502 );
        System.out.println("3D coords: " + cc3d.toString() );
    }
}
