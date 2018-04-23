/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.astronomy.test;

import eu.discoveri.astroninja.astronomy.projection.Celestial2DProjection;
import javafx.geometry.Point2D;

/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class CoordsTest
{
    public static void main(String[] args)
    {
        Point2D xy = Celestial2DProjection.EquatorialToCartesian( 0f, 0f, 0f, 0f, 1f, 0f, 89.67f );
        System.out.println("x,y: " +xy.getX()+ ", " +xy.getY());
    }
}
