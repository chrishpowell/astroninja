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
 * An entity cannot be created (written) because it is not unique.
 * @author Chris Powell, CPgraph Ltd.
 * @version 0.9
 *
 * @since 0.9
 */
public class NotUniqueException extends Exception
{
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Creates a new instance of
     * <code>NotUniqueException</code> without detail message.
     */
    public NotUniqueException() {}

    /**
     * Constructs an instance of
     * <code>NotUniqueException</code> with the specified detail message.
     *
     * @param message the detail message.
     */
    public NotUniqueException( String message ) {  super(message);  }
    //</editor-fold>
}
