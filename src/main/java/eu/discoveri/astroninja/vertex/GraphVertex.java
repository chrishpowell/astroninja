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
package eu.discoveri.astroninja.vertex;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Comparator;
import java.util.NoSuchElementException;

import com.fasterxml.jackson.annotation.JsonIgnore;

import eu.discoveri.astroninja.edge.Edge;
import eu.discoveri.astroninja.graph.GraphObjectImpl;
import eu.discoveri.astroninja.utils.CrispIUtil;
import eu.discoveri.astroninja.utils.FastCheck;
import java.awt.Color;


/**
 * GraphVertex, a vertex in the graph (not a 'stand-alone' entity).
 * Usually extended for generic concrete Vertex types, eg: Person
 *
 * @author Chris Powell, Discoveri OU
 * @email astrology.ninja
 * @version 0.9
 *
 * @since 0.9
 */
//<editor-fold defaultstate="collapsed" desc="Annotations and Queries">
@Entity
// NB: Versant does NOT support count() - which is annoying.  Hence VertexType.count
@NamedQueries({
    @NamedQuery(
        name="Vertex.findAll",
        query="SELECT v FROM GraphVertex v"
        ),
    @NamedQuery(
        name="Vertex.findWithName",
        query="SELECT v FROM GraphVertex v WHERE v.vname LIKE :vertexName"
        ),
    @NamedQuery(
        name="Vertex.count",
        query="SELECT count(v) FROM GraphVertex v"
        ),
    @NamedQuery(
        name="Vertex.countWithName",
        query="SELECT count(v) FROM GraphVertex v WHERE v.vname LIKE :vertexName"
        ),
})
//</editor-fold>
public abstract class GraphVertex extends GraphObjectImpl implements Vertex
{
    //<editor-fold defaultstate="collapsed" desc="Attributes">
    /** Name of the vertex */
    protected String    vname = null;
    /* Can rename or delete vertex? */
    private boolean     canRename = true;
    private boolean     canDelete = true;

    /** Edges of this vertex */
    @JsonIgnore
    protected List<Edge>edges = new ArrayList<>();
    /** Flag to filter for incoming,outgoing edges of this vertex */
    @JsonIgnore
    protected final static boolean INC = true, OUTG = false;
        
    // Visualization  (@TODO: Config these)
    private String  fill = CrispIUtil.getColourName(Color.LIGHT_GRAY),
                    stroke = CrispIUtil.getColourName(Color.BLACK);
    private int     defRadiusDT = 9, defRadiusMD = 6;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    protected GraphVertex(){}
    /**
     * Default constructor.  Defaults to allowing rename and delete of this vertex.
     *
     * @param vname name of this vertex
     */
    public GraphVertex( String vname )
    {
        this( vname, true, true );
    }
    /**
     * Constructor with name, rename and delete settings
     *
     * @param vname name of this Vertex
     * @param ren allow rename, <code>true</code> allows, <code>false</code> disallows
     * @param del allow delete of this vertex, <code>true</code> allows, <code>false</code> disallows
     */
    public GraphVertex( String vname, boolean ren, boolean del )
    {
        this.vname = vname;
        canRename = ren;
        canDelete = del;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Inner Classes: Iterators">
    /**
     * Returns an Iterable for all edges of this vertex.
     */
    public class EdgeIterator implements Iterable<Edge>
    {
        private List<Edge> es = getEdges();
        private Iterator<Edge> ies = es.iterator();
        private Vertex v = GraphVertex.this;
        private boolean selfV, otherV;
        private Class edgeClass, vertexClass;

        // Incoming edge? Incoming set true else false
        public EdgeIterator(boolean incoming, Class edgeClass, Class vertexClass)
        {
            this.otherV = (incoming)?false:true;
            this.selfV = (incoming)?true:false;
            this.edgeClass = edgeClass;
            this.vertexClass = vertexClass;
        }

        @Override
        public synchronized Iterator<Edge> iterator()
        {
            return new Iterator()
            {
                Edge next;
                @Override
                public boolean hasNext()
                {
                    Edge e;
                    // Next flag set in next()
                    synchronized(this){
                    if( next == null )
                    {
                        do
                        {  // Only interesed if V0/1 is this vertex and matches given class (if given)
                            if( !ies.hasNext() ) {  return false;  }
                            e = ies.next();
                        } while( (   edgeClass == null
                                  || FastCheck.isA(e, edgeClass))
                                && (   vertexClass == null
                                  || FastCheck.isA(e.getV(otherV), vertexClass))
                                && v != e.getV(selfV) );
                        next = e;
                    }}

                    return true;
                }

                @Override
                public Object next()
                {
                    if(!hasNext())
                        {  throw new NoSuchElementException();  }

                    synchronized(this){
                    Edge e = next;
                    next = null;
                    return e;}
                }

                @Override
                public void remove()
                {
                    throw new UnsupportedOperationException("no remove allowed");
                }
            };
        }
    }

    /**
     * Returns an Iterable for all connecting vertices of this vertex.
     */
    public class VertexIterator implements Iterable<Vertex>
    {
        private List<Edge> es = GraphVertex.this.getEdges();
        private Iterator<Edge> ies = es.iterator();
        private Vertex v = GraphVertex.this;
        private boolean selfV, otherV;
        private Class edgeClass, vertexClass;

        // Incoming edge? Incoming set true else false
        public VertexIterator(boolean incoming, Class edgeClass, Class vertexClass)
        {
            this.otherV = (incoming)?false:true;
            this.selfV = (incoming)?true:false;
            this.edgeClass = edgeClass;
            this.vertexClass = vertexClass;
        }

        @Override
        public synchronized Iterator<Vertex> iterator()
        {
            return new Iterator()
            {
                Edge next;
                @Override
                public boolean hasNext()
                {
                    Edge e;
                    // Next flag set in next()
                    synchronized(this){
                    if( next == null )
                    {
                        do
                        {  // Only interested if V0/1 is this vertex and matches given class (if given)
                            if( !ies.hasNext() ) {  return false;  }
                            e = ies.next();
                        } while( (   edgeClass == null
                                  || FastCheck.isA(e, edgeClass))
                                && (   vertexClass == null
                                  || FastCheck.isA(e.getV(otherV), vertexClass))
                                && v != e.getV(selfV) );
                        next = e;
                    }}

                    return true;
                }

                @Override
                public Object next()
                {
                    if(!hasNext())
                        {  throw new NoSuchElementException();  }

                    synchronized(this){
                    Edge e = next;
                    next = null;
                    return e.getV(otherV);}
                }

                @Override
                public void remove()
                {
                    throw new UnsupportedOperationException("no remove allowed");
                }
            };
        }
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
     * @return GraphVertex
     */
    public GraphVertex setFill(Color fill) { this.fill = CrispIUtil.getColourName(fill); return this; }
    /**
     * Fill colour based on name (eg:"steelblue") or rgb (eg:"0c0c0c").
     * 
     * @param colour 
     * @return GraphVertex
     */
    public GraphVertex setFill(String colour) { this.fill = colour; return this; }
    
    /**
     * Get current stroke colour
     * @return 
     */
    public String getStroke() { return stroke; }
    /**
     * Set stroke colour based on Color.
     * 
     * @param stroke
     * @return GraphVertex
     */
    public GraphVertex setStroke(Color stroke) { this.stroke = CrispIUtil.getColourName(stroke); return this; }
    /**
     * Stroke colour based on name (eg:"steelblue") or rgb (eg:"0c0c0c")
     * 
     * @param colour
     * @return GraphVertex
     */
    public GraphVertex setStroke(String colour) { this.stroke = colour; return this; }
    
    /**
     * Get default circle radius for desktop
     * @return 
     */
    public int getDefRadiusDT() { return defRadiusDT; }
    /**
     * Set default radius for desktop
     * 
     * @param defRadiusDT 
     * @return GraphVertex
     */
    public GraphVertex setDefRadiusDT(int defRadiusDT) { this.defRadiusDT = defRadiusDT; return this; }
    /**
     * Get default circle radius for Mobile Device
     * @return 
     */
    public int getDefRadiusMD() { return defRadiusMD; }
    /**
     * Set default circle radius for mobile device.
     * 
     * @param defRadiusMD 
     * @return GraphVertex
     */
    public GraphVertex setDefRadiusMD(int defRadiusMD) { this.defRadiusMD = defRadiusMD; return this; }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Vertex info">
    /** {@inheritDoc}
     * @Todo ??????? How populated ?????????
     */
    @Override
    public List<Edge> getEdges()
    {
        return edges;
    }

    /** {@inheritDoc}
     */
    @Override
    public void addEdge( Edge e )
    {
        edges.add( e );
    }

    /** {@inheritDoc}
     */
    @Override
    public void delEdge( Edge e)
    {
        edges.remove( e );
    }

    /** {@inheritDoc}
     */
    @Override
    public long degreeInVertex( Vertex v ) {  throw new UnsupportedOperationException();  };

    /** {@inheritDoc}
     */
    @Override
    public long degreeOutVertex( Vertex v ) {  throw new UnsupportedOperationException();  };

    /** {@inheritDoc}
     */
    @Override
    public long degreeVertex( Vertex v ) {  throw new UnsupportedOperationException();  };

    /** {@inheritDoc}
     */
    @Override
    public Iterable<Edge> getIncidentEdges(Class edgeClass, Class vertexClass)
    {
        return new EdgeIterator(INC, edgeClass, vertexClass);
    }

    /** {@inheritDoc}
     */
    @Override
    public Iterable<Edge> getEmanatingEdges(Class edgeClass, Class vertexClass)
    {
        return new EdgeIterator(OUTG, edgeClass, vertexClass);
    }

    /** {@inheritDoc}
     */
    @Override
    public Iterable<Vertex> getPredecessors(Class edgeClass, Class vertexClass)
    {
        return new VertexIterator(INC, edgeClass, vertexClass);
    }

    /** {@inheritDoc}
     */
    @Override
    public Iterable<Vertex> getSuccessors(Class edgeClass, Class vertexClass)
    {
        return new VertexIterator(OUTG, edgeClass, vertexClass);
    }

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
    public int compareTo(Object o)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Finders">
    /**
     * Find vertices with a given name and type.
     *
     * @param db   The database
     * @param name Name of a vertex (or vertices)
     * @return     List of vertices with given name/type
     */
    public static List<Vertex> findVertex( EntityManager db, Class<? extends GraphVertex> vertexClass, String name )
    {
        //        TypedQuery<GraphVertex> query =
        //          db.createNamedQuery("Vertex.findWithName", name);
        Query q = db.createQuery( "SELECT v FROM " +vertexClass.getSimpleName()+ " v WHERE v.vname = '" +name+ "'" );

        List<Vertex> lgv = q.getResultList();
        if( lgv == null )
        {  return new ArrayList<>();  }
        else
        {  return lgv;  }
    }

    /**
     * Find vertices with a given name.
     *
     * @param db  The database
     * @param name Name of vertex (or vertices)
     * @return List of vertices with given name
     */
    public static List<Vertex> findVertex( EntityManager db, String name )
    {
        return findVertex( db, GraphVertex.class, name );
    }

    /**
     * Find a vertex by Id
     * @param Id
     * @return Vertex with given Id
     * @throws UnsupportedOperationException
     */
    public static List<GraphVertex> findById( long Id )
            throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException( "Find Vertex by Id not written yet..." );
    }

    /**
     * Find all vertices by type.
     * @return List of vertices of given type
     * @throws UnsupportedOperationException
     */
    public static List<GraphVertex> findAll( Class<? extends Vertex> v )
            throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException( "Find all vertices by type not written yet..." );
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Comparator">
    /**
     * A Comparator class for vertices
     */
    public static Comparator<Vertex> COMPARATOR = new Comparator<Vertex>()
    {
        // This is where the sorting happens.
        @Override
        public int compare(Vertex v1, Vertex v2)
        {
            return v1.getVName().compareTo(v2.getVName());
        }
    };
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Mutators">
    /** {@inheritDoc}
     */
    @Override
    public String getVName() {  return vname;  }
    /** {@inheritDoc}
     */
    @Override
    public void setVName(String vname) {  this.vname = vname;  }

    /** {@inheritDoc}
     */
    public boolean isCanRename() {  return canRename;  }
    /** {@inheritDoc}
     */
    public void setCanRename(boolean canRename) {  this.canRename = canRename;  }

    /** {@inheritDoc}
     */
    public boolean isCanDelete() {  return canDelete;  }
    /** {@inheritDoc}
     */
    public void setCanDelete(boolean canDelete) {  this.canDelete = canDelete;  }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="toString">
    /**
     * Name and id of this Vertex
     *
     * @return Name and Id of this Vertex
     */
    @Override
    public String toString()
    {
        return getVName() +": "+getId();
    }
    //</editor-fold>
}