//<editor-fold defaultstate="collapsed" desc="Copyright">
/*
 * Copyright 2013-2014 Chris Powell.
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
 *
 * @author crispy
 */
public class NullEdgeException extends Exception
{

    /**
     * Creates a new instance of
     * <code>NullEdgeException</code> without detail message.
     */
    public NullEdgeException()
    {
    }

    /**
     * Constructs an instance of
     * <code>NullEdgeException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public NullEdgeException(String msg)
    {
        super(msg);
    }
}
