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
package eu.discoveri.astroninja.edge;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import eu.discoveri.astroninja.exception.NoSuchEntityTypeException;
import javax.persistence.*;

import java.util.List;

import eu.discoveri.astroninja.exception.NoSuchEntityWithIdException;
import eu.discoveri.astroninja.exception.NullVertexException;
import eu.discoveri.astroninja.graph.GraphObjectImpl;
import static eu.discoveri.astroninja.graph.GraphObjectImpl.getOM;
import eu.discoveri.astroninja.utils.CrispIUtil;
import eu.discoveri.astroninja.vertex.Tag;
import eu.discoveri.astroninja.vertex.Vertex;
import java.awt.Color;


/**
 * GraphEdge, an implementation of Edge (by default a directed edge {@link EdgeDirected EdgeDirected}.
 * To subclass as an undirected edge, set the <code>EdgeDirected</code> annotation to 'false'
 * <p>
 * Note: For weighted edges, the concrete class should override compareTo().
 * </p>
 * @author Chris Powell, CPgraph Ltd.
 */
//<editor-fold defaultstate="collapsed" desc="Annotations">
@Entity
@EdgeDirected(directed=true)
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Named Queries">
// NB: Versant does NOT support count() - which is annoying.  Hence EdgeType.count
@NamedQueries({
    @NamedQuery(
        name="Edge.findAll",
        query="SELECT e FROM GraphEdge e"
        ),
    @NamedQuery(
        name="Edge.count",
        query="SELECT count(e) FROM GraphEdge e"
        ),
    @NamedQuery(
        name="EdgeType.count",
        query="SELECT ec FROM :edgeType ec"
        )
})
//</editor-fold>
public abstract class GraphEdge extends GraphObjectImpl implements Edge
{
    //<editor-fold defaultstate="collapsed" desc="Constants and variables">
    @JsonProperty(value="source")   // NB: property name for d3 forcesimulation
    protected Vertex   v0;          // For directed, originating node
    @JsonProperty(value="target")   // NB: property name for d3 forcesimulation
    protected Vertex   v1;          // For directed, remote vertex
    protected Tag      qualifier;   // Qualifies an Edge with a Tag
    protected Weight   weight;      // User supplied attribute
    
    // Visualization  (@TODO: Config these)
    private String  fill = CrispIUtil.getColourName(Color.GRAY);
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * No-arg constructor
     */
    protected GraphEdge(){}

    /**
     * Constructor for unweighted edges.
     *
     * @param v0  For directed edge, originating vertex
     * @param v1  For directed edge, remote vertex
     * @throws NullVertexException
     */
    public GraphEdge( Vertex v0, Vertex v1 )
            throws NullVertexException
    {
        this( v0,v1, null );
    }
    /**
     * Constructor for weighted edges.
     *
     * @param v0  For directed edge, originating vertex
     * @param v1  For directed edge, remote vertex
     * @param weight  User added attribute (eg: max. number messages allowed)
     * @throws NullVertexException
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public GraphEdge( Vertex v0, Vertex v1, Weight weight )
            throws NullVertexException
    {
        if( v0 == null || v1 == null )
            {  throw new NullVertexException( "An Edge cannot connect to null vertices" );  }
        this.v0 = v0;
        this.v1 = v1;
        this.weight = weight;

        // Add this edge to the vertices
        v0.addEdge( this );
        v1.addEdge( this );
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Edge logic">
    /** {@inheritDoc}
     */
    @Override
    public void delEdge()
    {
        v0.getEdges().remove(this);
        v1.getEdges().remove(this);
    }

    /** {@inheritDoc}
     * @TODO: Implement
     */
    @Override
    public Vertex getMate( Vertex vertex ) {  throw new UnsupportedOperationException();  }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Finders">
    /**
     * Find all edges with given name.
     * @param db The database
     * @param name The name of the edge(s)
     * @return List of edges
     */
    public static List<Edge> findByName( EntityManager db, String name )
            throws NoSuchEntityTypeException
    {
        //        TypedQuery<GraphVertex> query =
        //          db.createNamedQuery("Vertex.findWithName", name);
        Query q = db.createQuery( "SELECT e FROM GraphEdge e WHERE e.vname = " +name );

        List<Edge> lgv = q.getResultList();
        if( lgv == null )
        {  throw new NoSuchEntityTypeException();  }
        else
        {  return lgv;  }
    }

    /**
     * Find an edge by Id.
     * @param Id The Id of the edge
     * @return Edge with this Id
     * @throws UnsupportedOperationException
     * @throws NoSuchEntityWithIdException
     */
    public static Edge findById( long Id )
            throws UnsupportedOperationException, NoSuchEntityWithIdException
    {
        throw new UnsupportedOperationException( "Find Edge by Name not written yet..." );
    }

    /**
     * Find all edges by given type.
     * @return List of edges of given type
     * @throws UnsupportedOperationException
     * @throws NoSuchEntityTypeException
     */
    public static List<Edge> findAll( Class<? extends Edge> e )
            throws UnsupportedOperationException, NoSuchEntityTypeException
    {
        throw new UnsupportedOperationException( "Find all edges not written yet..." );
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Visualization">
    /**
     * Get current fill colour
     * @return
     */
    public String getFill() { return fill; }
    /**
     * Fill colour based on Color.
     *
     * @param fill
     */
    public void setFill(Color fill) { this.fill = CrispIUtil.getColourName(fill); }
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Mutators">
    /** {@inheritDoc}
     */
    @Override
    public Vertex getV0() {  return v0;  }

    /** {@inheritDoc}
     */
    @Override
    public Vertex getV1() {  return v1;  }

    /** {@inheritDoc}
     */
    @Override
    public Vertex getV( boolean farV )
    {
        return farV ? getV1() : getV0();
    }

    /** {@inheritDoc}
     */
    @Override
    public Tag getQualifier() {  return qualifier;  }

    /** {@inheritDoc}
     */
    @Override
    public void setQualifier( Tag qualifier ) {  this.qualifier = qualifier;  }

    /** {@inheritDoc}
     */
    @Override
    public Weight getWeight() {  return weight;  }

    /** {@inheritDoc}
     */
    @Override
    public void setWeight( Weight weight ) {  this.weight = weight;  }

    /** {@inheritDoc}
     */
    @Override
    public boolean isDirected()
    {
        EdgeDirected directed = (EdgeDirected)this.getClass().getAnnotation(EdgeDirected.class);
        return directed.directed();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Simple JSON for d3 forceSim">
    /**
     * Generate simple d3 JSON for links.
     * Eg: {"source":"Fred","target":"Bloggs","fill":"blue","etype":"Follows"}
     * 
     * @return SImple JSON string
     */
    public String toJSONVNameOnly()
    {
        return "{\"source\":\"" +v0.getVName()+ "\",\"target\":\"" +v1.getVName()+ "\",\"fill\":\"" +fill+ "\",\"etype\":\"" +this.getClass().getSimpleName()+ "\"}";
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="CompareTo">
    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less than,
     * equal to, or greater than the specified object.
     *
     * @see java.lang.Comparable java.lang.Comparable
     *
     * @param o the Vertex object to be compared
     * @return A negative integer, zero, or a positive integer as this object is
     * less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo( Edge e )
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    //</editor-fold>
}
