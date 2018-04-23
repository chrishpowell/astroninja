/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */
package eu.discoveri.astroninja.exception;

/**
 *
 * @author Chris Powell, Discoveri OU
 */
public class DegreesOutOfRangeException extends Exception
{
    /**
     * Creates a new instance of <code>DegreesOutOfRangeException</code> without
     * detail message.
     */
    public DegreesOutOfRangeException() {}

    /**
     * Constructs an instance of <code>DegreesOutOfRangeException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public DegreesOutOfRangeException(String msg) { super(msg); }
}
