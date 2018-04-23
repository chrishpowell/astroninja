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
 * No vertex for an edge.  An 'empty' vertex class has been encountered, perhaps
 * in constructing an edge or a vertex was deleted after the edge was placed in
 * memory.
 *
 * @author Chris Powell, CPgraph Ltd.
 * @version 0.9
 *
 * @since 0.9
 */
public class NullVertexException extends Exception
{
    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Creates a new instance of
     * <code>NullVertexException</code> without detail message.
     */
    public NullVertexException(){}

    /**
     * Constructs an instance of
     * <code>NullVertexException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public NullVertexException(String msg) {  super(msg);  }
    //</editor-fold>
}
