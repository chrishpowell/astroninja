/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */
package eu.discoveri.astroninja.exception;

/**
 *
 * @author Chris Powell, Discoveri OU
 */
public class ServerAdminException extends Exception
{

    /**
     * Creates a new instance of <code>ServerAdminException</code> without
     * detail message.
     */
    public ServerAdminException() {
    }

    /**
     * Constructs an instance of <code>ServerAdminException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ServerAdminException(String msg) {
        super(msg);
    }
}
