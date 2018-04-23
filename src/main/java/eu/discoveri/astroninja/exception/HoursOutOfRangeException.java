/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */
package eu.discoveri.astroninja.exception;

/**
 *
 * @author Chris Powell, Discoveri OU
 */
public class HoursOutOfRangeException extends Exception
{
    /**
     * Creates a new instance of <code>HoursOutOfRangeException</code> without
     * detail message.
     */
    public HoursOutOfRangeException() {}

    /**
     * Constructs an instance of <code>HoursOutOfRangeException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public HoursOutOfRangeException(String msg) { super(msg); }
}
