/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */
package eu.discoveri.astroninja.exception;

/**
 *
 * @author Chris Powell, Discoveri OU
 */
public class DuplicateStarException extends Exception
{
    /**
     * Creates a new instance of <code>DuplicateStarException</code> without
     * detail message.
     */
    public DuplicateStarException() {}

    /**
     * Constructs an instance of <code>DuplicateStarException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DuplicateStarException(String msg)
    {
        super(msg);
    }
}
