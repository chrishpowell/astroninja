/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */
package eu.discoveri.astroninja.exception;

/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class InvalidISOCodeLengthException extends Exception
{
    /**
     * Creates a new instance of <code>InvalidISOCodeLengthException</code>
     * without detail message.
     */
    public InvalidISOCodeLengthException() {}

    /**
     * Constructs an instance of <code>InvalidISOCodeLengthException</code> with
     * the specified detail message.
     *
     * @param msg the detail message.
     */
    public InvalidISOCodeLengthException(String msg)
    {
        super(msg);
    }
}
