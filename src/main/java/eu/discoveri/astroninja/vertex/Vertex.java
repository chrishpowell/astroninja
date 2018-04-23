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
package eu.discoveri.astroninja.vertex;

import eu.discoveri.astroninja.edge.Edge;
import eu.discoveri.astroninja.graph.GraphObject;
import java.util.Comparator;

import java.util.List;


/**
 * Vertex interface.
 * Note: the degree of a vertex in a graph is the number of edges incident on that
 * vertex.  A directed graph has out- and in-degree edges.
 *
 * @author Chris Powell, CPgraph Ltd.
 */
public interface Vertex extends GraphObject, Comparable
{
    /**
     * Get the Vertex name.
     * @return Name of this Vertex
     */
    String getVName();

    /**
     * Set the Vertex name.
     * @param vname Name for this Vertex
     */
    void setVName( String vname );

    /**
     * Get all edges of this vertex.
     * @return A List of all Edges of this Vertex
     */
    List<Edge> getEdges();

    /**
     * Add a (hyper-) Edge to this Vertex.  Essentially this defines a <i>hyper-</i>edge,
     * that is, an edge which connects to more than one vertex.
     * @param e Existing edge to add
     */
    void addEdge( Edge e );

    /**
     * Delete an edge of this Vertex.  Also deletes any references to this edge in
     * connecting vertices.  Note, can leave orphaned vertices.
     * @param e Edge to delete
     */
    void delEdge( Edge e);

    /**
     * Check if vertex can be renamed.
     * @return  <code>true</code> if renameable, <code>false</code> otherwise
     */
    boolean isCanRename();
    /**
     * Set whether vertex can be renamed.
     * @param canRename set <code>true</code> if allowed to be renamed,
     * <code>false</code> otherwise
     */
    void setCanRename(boolean canRename);

    /**
     * Check if vertex can be deleted.
     * @return  <code>true</code> if deleteable, <code>false</code> otherwise
     */
    boolean isCanDelete();
    /**
     * Set whether vertex can be deleted.
     * @param canDelete set <code>true</code> if allowed to be deleted,
     * <code>false</code> otherwise
     */
    void setCanDelete(boolean canDelete);

    /**
     * Determine count of incoming edges on this vertex.
     * @param v the Vertex in question
     * @return Count of incoming edges
     */
    long degreeInVertex( Vertex v );

    /**
     * Determine count of outgoing edges on this vertex.
     * @param v the Vertex in question
     * @return Count of outgoing edges
     */
    long degreeOutVertex( Vertex v );

    /**
     * Determine count of undirected edges on this vertex.
     * @param v the Vertex in question
     * @return Count of undirected edges
     */
    long degreeVertex( Vertex v );

    //static Comparator<Vertex> COMPARATOR = new Comparator<Vertex>(){};

    /**
     * Incoming edges, formally elements of I(v) <code>[I(v): Incident/incoming edges of vertex v]</code>.
     * Note: For undirected, <code>Incident(u,v)&lt;=&gt;Emanating(v,u)</code>
     *
     * @param edgeClass Match to subclass of Edge
     * @param vertexClass match to subclass of Vertex
     * @return Iterable of edges of edgeClass
     */
    Iterable<Edge> getIncidentEdges(Class edgeClass, Class vertexClass);

    /**
     * Outgoing edges, formally elements of A(v) <code>[A(v): emanating/outgoing edges of vertex v]</code>.
     * Note: For undirected, <code>Incident(u,v)&lt;=&gt;Emanating(v,u)</code>
     *
     * @param edgeClass Match to subclass of Edge
     * @param vertexClass Match to subclass of Vertex
     * @return Iterable of edges of edgeClass
     */
    Iterable<Edge> getEmanatingEdges(Class edgeClass, Class vertexClass);

    /**
     * Elements of P(v), the set of remote vertices on incident edges.
     * That is, <code>P(v)={u:(u,v) in I(v)}</code>
     *
     * @param edgeClass Match to subclass of Edge
     * @param vertexClass Match to subclass of Vertex
     * @return Iterable of vertices  of vertexClass
     */
    Iterable<Vertex> getPredecessors(Class edgeClass, Class vertexClass);

    /**
     * Elements of S(v), the set of remote vertices on emanating edges.
     * That is, <code>S(v)={w:(w,v) in A(v)}</code>
     *
     * @param edgeClass Match to subclass of Edge
     * @param vertexClass Match to subclass of Vertex
     * @return Iterable of vertices  of vertexClass
     */
    Iterable<Vertex> getSuccessors(Class edgeClass, Class vertexClass);

    /**
     * Make sure we have a decent output to display
     */
    @Override
    String toString();
}
