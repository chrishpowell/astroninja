/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */
package eu.discoveri.astroninja.astronomy;

/**
 * A StarPair for linking for drawing constellation.
 * 
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class StarPair
{
    private final ConstlStar s1, s2;

    /**
     * Constructor.
     * 
     * @param s1
     * @param s2 
     */
    public StarPair(ConstlStar s1, ConstlStar s2)
    {
        this.s1 = s1;
        this.s2 = s2;
    }

    public ConstlStar getS1() { return s1; }
    public ConstlStar getS2() { return s2; }
}
