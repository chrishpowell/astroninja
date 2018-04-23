/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.test.throwaway;

import java.util.Formatter;

/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class StringBufTest
{
    public static void main(String[] args)
    {
        StringBuilder sbuf = new StringBuilder();
        Formatter fmt = new Formatter(sbuf);
        fmt.format("PI = %f", Math.PI);
        System.out.print(sbuf.toString());
        fmt.close();
        fmt.format("[%s] is go","Fred");
        System.out.print(sbuf.toString());
    }
}
