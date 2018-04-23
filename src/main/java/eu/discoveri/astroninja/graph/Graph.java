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
package eu.discoveri.astroninja.graph;

//<editor-fold defaultstate="collapsed" desc="imports">
import javax.persistence.EntityManager;

import eu.discoveri.astroninja.vertex.Vertex;
import eu.discoveri.astroninja.vertex.GraphVertex;
import eu.discoveri.astroninja.edge.Edge;
import eu.discoveri.astroninja.edge.GraphEdge;
import eu.discoveri.astroninja.ooUtils.Strategy;
import eu.discoveri.astroninja.ooUtils.PrePostStrategy;
import eu.discoveri.astroninja.edge.Weight;
import eu.discoveri.astroninja.exception.NoConnectingEdgeException;
import eu.discoveri.astroninja.exception.NoSuchDatabaseException;
import eu.discoveri.astroninja.exception.NoSuchEntityWithIdException;
import eu.discoveri.astroninja.exception.ServerAdminException;
import java.io.IOException;

import java.util.Collection;
import java.util.List;
//</editor-fold>

/**
 * Graph interface
 * Graph is represented as the pair (V,E) being the Vertex and Edge sets
 * respectively. See {@link Vertex Vertex} and {@link Edge Edge} interfaces.
 *
 * @author Chris Powell, CPgraph Ltd.
 */
public interface Graph
{
    //<editor-fold defaultstate="collapsed" desc="Basic Graph methods">
    /**
     * Create a database.  Based on persistence.xml properties
     *
     * @throws ServerAdminException
     * @throws IOException
     */
    void createGraph()
            throws ServerAdminException, IOException;

    /**
     * Open a 'default' graph
     * @return Database cache handle
     */
    EntityManager openGraph();

    /**
     * Delete a graph based on current database URL, see {@link #getDbURL() dbURL}
     *
     * @throws ServerAdminException
     * @throws NoSuchDatabaseException
     * @throws IOException
     */
    void deleteGraph()
            throws ServerAdminException, NoSuchDatabaseException, IOException;

    /**
     * Close the graph
     */
    void closeGraph();
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Get info">
    /**
     * Get the persistence unit (PU) being used.
     * @return Persistence Unit string
     */
    String getPersistUnit();

    /**
     * Get underlying database URL.  (Could be file or persistence unit
     * depending on JPA used)
     * @return database URL
     */
    String getDbURL();

    /**
     * Get database name
     * @return Name given to this database
     */
    public String getName();

    /**
     * Get the total number of objects of type c
     *
     * @param c The object being counted
     * @return Number of objects of given class
     */
    long getNumberOfObjects( Class c );

    /**
     * Get vertices of type v
     * @param v type of vertex (eg: 'Person')
     * @return List of vertices of given type
     */
    List<Vertex> getVertices( Vertex v );

    /**
     * Get edges of type e
     * @param e type of edge (eg: 'Follows')
     * @return List of edges of given type
     */
    List<Edge> getEdges( Edge e );

    /**
     * Get all vertices
     * @return List of all vertices
     */
    List<GraphVertex> getAllVertices();

    /**
     * Get all vertices (Vertex.class) and detach
     * @return List of all vertices which are then detached
     */
    Collection<GraphVertex> getAllVerticesDetached();

    /**
     * Get all edges
     * @return List of all edges
     */
    List<GraphEdge> getAllEdges();
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Edges and Vertices">
    /**
     * Add a Vertex to the graph.
     * @param v the vertex being added
     * @return Id of vertex
     */
    long addVertex( Vertex v );

    /**
     * Get vertex with given id.
     * @param v Id of vertex
     * @return Vertex object
     * @throws NoSuchEntityWithIdException if a <i>vertex</i> with this Id cannot
     * be found.  The Id may be referring to another type of object.
     */
    Vertex getVertex( long v )
            throws NoSuchEntityWithIdException;

    /**
     * Get edge with given id.
     * @param v Id of edge
     * @return Edge object
     * @throws NoSuchEntityWithIdException if an <i>edge</i> with this Id cannot
     * be found.  The Id may be referring to another type of object.
     */
    Vertex getEdge( long v )
            throws NoSuchEntityWithIdException;

    /**
     * Add an edge in the graph. The two vertices can refer to the
     * same vertex (a 'self-link').
     * @param from vertex with (outgoing) edge
     * @param to vertex with (incoming) edge
     * @return vertex id
     */
    long addEdge( Vertex from, Vertex to );

    /**
     * Add edge in the graph with given Weight. The two vertices can refer to the
     * same vertex (a 'self-link').
     * @param from vertex with (outgoing) edge
     * @param to vertex with (incoming) edge
     * @param weight the 'weight' of this edge
     * @return Id of edge connecting vertices
     */
    long addEdge( Vertex from, Vertex to, Weight weight );

    /**
     * Get edges connecting given vertices.  The two vertices can refer to the
     * same vertex (a 'self-link').
     * @param v any vertex
     * @param w any vertex
     * @return List of connecting edges (if any exist)
     * @throws NoConnectingEdgeException
     */
    List<Edge> getEdges( long v, long w )
            throws NoConnectingEdgeException;

    /**
     * Delete given vertex from graph.
     * Will also delete all edges of the vertex and all references to these
     * edges in connecting vertices
     * @param v the vertex being deleted
     */
    void deleteVertex( Vertex v );

    /**
     * Delete an Edge.  Note, vertices can be left orphaned (they function as a
     * non-graph entity). Note this method also deletes all references to this edge
     * in connecting vertices
     * @param e the edge being deleted
     */
    void deleteEdge( Edge e );
    //</editor-fold>

    /*
    boolean isEdge( int v, int w );
    boolean isConnected();
    boolean isCyclic();
    */

    //<editor-fold defaultstate="collapsed" desc="Traversals">
    /**
     * Traverses a graph using Depth First from a start vertex.  Uses a visitor
     * pattern to apply methods to visited vertices.
     *
     * @param visitor   Class to be executed
     * @param start         Vertex from where to start
     */
    void depthFirstTraversal( PrePostStrategy visitor, Vertex start );

    /**
     * Traverses a graph using Breadth First from a start vertex.  Uses a visitor
     * pattern to apply methods to visited vertices.
     *
     * @param visitor   Class to be executed
     * @param start         Vertex from where to start
     */
    void breadthFirstTraversal( Strategy visitor, Vertex start );
    //</editor-fold>
}
