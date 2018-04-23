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
package eu.discoveri.astroninja.hierarchy;

import eu.discoveri.astroninja.edge.Edge;
import eu.discoveri.astroninja.exception.NotEdgeTypeException;
import eu.discoveri.astroninja.exception.NullVertexException;
import eu.discoveri.astroninja.vertex.Vertex;
import eu.discoveri.astroninja.utils.FastCheck;
import eu.discoveri.astroninja.vertex.GraphVertex;


/**
 * Utilities for performing hierarchical actions on Vertex items.
 * Delegated by Hierarchy interface implementations.
 *
 * @author Chris Powell, CPgraph Ltd.
 */
public class VertexHierarchy<T extends Edge> extends GraphVertex implements Hierarchy
{
  private Class<T> clazz = null;

    /**
     * Constructor which defines the Edge type for the hierarchy.
     *
     * @param clazz Class of type that extends Edge.
     * @throws NotEdgeTypeException if class is <code>null</code> or is not of a type that extends <code>Edge</code>
     */
    public VertexHierarchy( Class<T> clazz )
            throws NotEdgeTypeException
    {
        if( clazz == null || !clazz.isAssignableFrom(Edge.class) )
            {  throw new NotEdgeTypeException("Expecting class that extends Edge");  }

        this.clazz = clazz;
    }

    //<editor-fold defaultstate="collapsed" desc="Hierarchy interface">
    /** {@inheritDoc}
     */
    @Override
    public Vertex getParent( Vertex v )
            throws NullVertexException
    {
        for( Edge eachEdge: v.getEdges() )
        {
            // Check hierarchy against this edge type
            if( FastCheck.isA(eachEdge, clazz) )
            {
                T et = (T)eachEdge;
                if( et.getV1() == v )
                    {  return et.getV0();  }
            }
        }

        throw new NullVertexException();
    }

    /** {@inheritDoc}
     */
    @Override
    public int getSiblingCount( Vertex v )
            throws NullVertexException
    {
        Vertex parent = getParent(v);
        int count = 0;
        if( parent != null )
        {
            for( Edge eachEdge: v.getEdges() )
            {
                if( FastCheck.isA(eachEdge, clazz) )
                {
                    T et = (T)eachEdge;
                    if( et.getV0() == parent && et.getV1() != v )
                        {  count++;  }
                }
            }
        }
        return count;
    }
    //</editor-fold>
}
