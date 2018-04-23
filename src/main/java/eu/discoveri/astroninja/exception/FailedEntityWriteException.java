//<editor-fold defaultstate="collapsed" desc="Copyright">
/*
 * Copyright 2013-2014 Chris Powell, CPgraph Ltd..
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
//</editor-fold>
package eu.discoveri.astroninja.exception;

/**
 * Could not write entity to the database.
 * @author Chris Powell, CPgraph Ltd.
 * @version 0.9
 *
 * @since 0.9
 */
public class FailedEntityWriteException extends Exception
{
    /**
     * Creates a new instance of
     * <code>FailedEntityWriteException</code> without detail message.
     */
    public FailedEntityWriteException()
    {
    }

    /**
     * Constructs an instance of
     * <code>FailedEntityWriteException</code> with the specified detail
     * message.
     *
     * @param msg the detail message.
     */
    public FailedEntityWriteException(String msg)
    {
        super(msg);
    }

    /**
     * Constructs an instance of
     * <code>FailedEntityWriteException</code> with the specified detail
     * message and cause
     *
     * @param msg the detail message
     * @param cause  the cause (which is saved for later retrieval by the
     * Throwable.getCause() method). (A null value is permitted, and indicates
     * that the cause is nonexistent or unknown)
     */
    public FailedEntityWriteException( String msg, Throwable cause )
    {
        super(msg,cause);
    }
}
