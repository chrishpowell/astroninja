//<editor-fold defaultstate="collapsed" desc="Copyright">
/*
 * Copyright 2012 Chris Powell, CPgraph Ltd..
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
package eu.discoveri.astroninja.hierarchy;

import eu.discoveri.astroninja.exception.NullVertexException;
import eu.discoveri.astroninja.vertex.Vertex;


/**
 * Enforces hierarchical methods (such as <code>getParent(), getSiblingCount()</code>
 * etc.).  A hierarchy expects <code>&lt;P Parent vertex type, T child of Parent vertex type&gt;</code>.
 * This allows different types to be in a hierarchy, eg:
 *    <code>|Garage|--contains-->|Vehicle|--sports-->|LicensePlate|</code>
 *
 * @author Chris Powell, CPgraph Ltd.
 */
public interface Hierarchy<P extends Vertex, T extends Vertex>
{
    /**
     * Get the parent in the hierarchy.
     * Note that a hierarchy is designated by an Edge type and that any one
     * Vertex can be in more than one hierarchy.
     * ***** This needs to enforce OneToMany ***********
     *
     * @param start the start vertex
     * @return Parent vertex
     * @throws NullVertexException if there is no parent for the given vertex
     */
    P getParent( T start )
            throws NullVertexException;

    /**
     * Count of siblings of given vertex
     *
     * @param v vertex for which sibling count will be determined
     * @return Count of siblings
     * @throws NullVertexException if there is no parent for the given vertex
     */
    int getSiblingCount( P v )
            throws NullVertexException;
}
