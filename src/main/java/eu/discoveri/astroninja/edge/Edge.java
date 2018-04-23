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
package eu.discoveri.astroninja.edge;

import eu.discoveri.astroninja.graph.GraphObject;
import eu.discoveri.astroninja.vertex.Tag;
import eu.discoveri.astroninja.vertex.Vertex;


/**
 * Edge interface.
 * For a Graph (V,E) [a set of Vertices and Edges], the edge set E consists of
 * pairs of vertices {u,v} where {u,v}inV and u!=v. Note: {u,v} or (u,v)
 * [by convention] is considered the same edge as (v,u).  In a directed graph,
 * (u,v) denotes an edge where it leaves (or is incident from) vertex u, and
 * enters (or is incident to) vertex v.
 *
 * @author Chris Powell, CPgraph Ltd.
 */
public interface Edge extends GraphObject, Comparable<Edge>
{
    /**
     * Vertex at near end of this Edge...
     *
     * @return Vertex at near end of Edge
     */
    Vertex getV0();
    /**
     * ...and Vertex at remote end of this Edge
     *
     * @return Vertex at remote end of Edge
     */
    Vertex getV1();

    /**
     * Get far or near vertex of this edge.
     * @todo Check this getV() method!
     *
     * @param farV  Set <code>true</code> to get far vertex, <code>false</code> to get near vertex
     * @return Vertex
     */
    Vertex getV(boolean farV);

    /**
     * Get the qualifier (Tag) for this Edge
     *
     * @return Tag qualifier (if any) for this Edge
     */
    Tag getQualifier();

    /**
     * Set the Tag qualifier for this Edge
     *
     * @param tag Tag to be attributed to this Edge
     */
    void setQualifier( Tag tag );

    /**
     * Delete this Edge.  Note, can leave orphaned vertices.
     * @todo: Error handling
     */
    void delEdge();

    /**
     * The {@link Weight Weight} for this edge. For example:  allows only 11
     * messages to pass to the CricketTeam
     *
     * @return Weight attributed to the Edge
     */
    Weight getWeight();

    /**
     * Set the weight of this Edge
     *
     * @param weight Weight (user class) to be attributed to this Edge
     */
    void setWeight( Weight weight );

    /**
     * Determine if edge is directed.  See {@link EdgeDirected EdgeDirected}
     *
     * @return True if directed edge, False if bidirectional/undirected
     */
    boolean isDirected();

    /**
     * Satisfies getting 'other' vertex.
     * That is, for class c:<br/>
     * <code>
     *   &nbsp;c.getMate(c.getV0()) => c.getV1() and <br/>
     *   &nbsp;c.getMate(c.getV1()) => c.getV0()
     * </code>
     *
     * @return Vertex 'at other end' of this Edge
     */
    Vertex getMate( Vertex vertex );

    /**
     * Compare edges (using {@link Weight Weight} normally).
     * @param e the 'other' edge to this
     * @return -1, 0, 1
     */
    int compareTo( Edge e );
}
