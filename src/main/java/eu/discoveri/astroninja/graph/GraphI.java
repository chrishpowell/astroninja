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
import eu.discoveri.astroninja.config.AllConfig;
import eu.discoveri.astroninja.config.Const;
import eu.discoveri.astroninja.vertex.Vertex;
import eu.discoveri.astroninja.vertex.GraphVertex;
import eu.discoveri.astroninja.edge.Edge;
import eu.discoveri.astroninja.edge.GraphEdge;
import eu.discoveri.astroninja.ooUtils.AbstractContainer;
import eu.discoveri.astroninja.ooUtils.Container;
import eu.discoveri.astroninja.ooUtils.PrePostStrategy;
import eu.discoveri.astroninja.ooUtils.Strategy;
import eu.discoveri.astroninja.edge.Weight;
import eu.discoveri.astroninja.exception.NoConnectingEdgeException;
import eu.discoveri.astroninja.exception.NoSuchDatabaseException;
import eu.discoveri.astroninja.exception.NoSuchEntityWithIdException;
import eu.discoveri.astroninja.exception.ServerAdminException;
import eu.discoveri.astroninja.oodb.ServerAdministration;
import eu.discoveri.astroninja.utils.PersistenceXML;
import java.io.IOException;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
//</editor-fold>

/**
 * GraphI.  The main interface to the underlying graph.  Note ServerAdministration
 * may need to be 'tweaked' depending on underlying oodb (currently tested against
 * Versant and ObjectDB).
 * 
 * Note EntityManager is not thread safe hence the use of ThreadLocal below.  Note
 * also that this is a preferred method for a servlet to run its own session /
 * EntityManager
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public final class GraphI extends AbstractContainer implements Graph
{
    //<editor-fold defaultstate="collapsed" desc="Attributes">
    // Usable name of database
    private final String                            name;
    // database URL from persistence XML
    private final String                            dbURL;
    // Persistence Unit (PU) and properties for PU (if reqd.)
    private final String                            persistUnit = AllConfig.baseconfig().getDatabase().getDefPersistUnit(),
                                                    connectionURL = AllConfig.baseconfig().getDatabase().getConnectionURL(),
                                                    pJPAxmlProp = AllConfig.baseconfig().getDatabase().getPJPAxmlProp();
    // Entity Manager factory
    private final EntityManagerFactory              db;
    // Reference/handle to database
    private static final ThreadLocal<EntityManager> tloc = new ThreadLocal<>();
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Default graph constructor.
     * 
     * @Todo: Sort out constructors
     * @param name Name of database
     * @throws Exception
     */
    public GraphI( String name )
            throws Exception
    {
        // Database name
        this.name   = name;

        // Entity Manager Factory
        //        db = Persistence.createEntityManagerFactory( SynchronizationType.SYNCHRONIZED, persistUnit );
        db = Persistence.createEntityManagerFactory( persistUnit );

        // Database URL
        dbURL = PersistenceXML.getDatabaseURLFromPersistenceUnit( null, persistUnit, connectionURL );
    }

    /**
     * Construct a graph from a Persistence Unit XML using default PU and give
     * the graph a name.
     * 
     * @param pXMLfile Name of persistence unit XML file
     * @param name Name of database
     * @throws Exception
     */
    public GraphI( String pXMLfile, String name )
            throws Exception
    {
        // Database1 name
        this.name   = name;

        // Entity Manager Factory
        db = Persistence.createEntityManagerFactory( persistUnit );
        // Database1 URL
        dbURL = PersistenceXML.getDatabaseURLFromPersistenceUnit( pXMLfile, persistUnit, connectionURL );
    }

    /**
     * Construct a graph from a Persistence Unit XML using given PU and give
     * the graph a name.
     * 
     * @param pXMLfile Name of persistence unit XML file
     * @param name Name of database
     * @param pUnit name of persistence unit
     * @throws Exception
     */
    public GraphI( String pXMLfile, String name, String pUnit )
            throws Exception
    {
        // Database name
        this.name = name;

//        System.out.println( "pUnit: " +pUnit+ ", def PU: " +persistUnit+ ", JPA props: " +vJPAxmlProp );
        // Entity Manager Factory
        db = Persistence.createEntityManagerFactory( pUnit );
        // Database URL
        dbURL = PersistenceXML.getDatabaseURLFromPersistenceUnit( pXMLfile, pUnit, connectionURL );
    }

    /**
     * Construct a graph from a Persistence Unit XML using given PU, give
     * the graph a name and attach any additional properties
     * 
     * @param pXMLfile Name of persistence unit XML file
     * @param name Name of database
     * @param pUnit name of persistence unit
     * @param propsXML properties
     * @throws Exception
     */
    public GraphI( String pXMLfile, String name, String pUnit, String propsXML )
            throws Exception
    {
        // Database name
        this.name   = name;

        // Entity Manager Factory
        db = Persistence.createEntityManagerFactory( pUnit, (Map)(new HashMap().put(pJPAxmlProp,propsXML)) );
        // Database URL
        dbURL = PersistenceXML.getDatabaseURLFromPersistenceUnit( pXMLfile, pUnit, connectionURL );
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Graph methods">
    /** {@inheritDoc}
     */
    @Override
    public void createGraph()
            throws ServerAdminException, IOException
    {
        // Check to see if it's still there
        if( ServerAdministration.verifyDatabaseExists(dbURL) )
        {
            ServerAdministration.removeDatabase( dbURL );
        }

        // And create a new one
        ServerAdministration.createDatabase( dbURL );
    }

    /** {@inheritDoc}
     */
    @Override
    public synchronized EntityManager openGraph()
    {
        EntityManager cache = tloc.get();
        if( cache == null )
        {
            cache = db.createEntityManager();
            tloc.set( cache );
        }

        return cache;
    }

    /** {@inheritDoc}
     */
    @Override
    public void deleteGraph()
            throws ServerAdminException, NoSuchDatabaseException, IOException
    {
        // Check to see if it's there
        if( !ServerAdministration.verifyDatabaseExists(dbURL) )
        {
            throw new NoSuchDatabaseException( dbURL );
        }

        // If not, do the deed
        ServerAdministration.removeDatabase( dbURL );
    }

    /**{@inheritDoc}
     */
    @Override
    public synchronized void closeGraph()
    {
        EntityManager cache = tloc.get();
        if( cache != null )
        {
            cache.close();  // Could there be more than one?
            tloc.set( null );
        }
        
        // Close Factory
        db.close();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Static methods">
    /**
     * Get the db handle.
     * @return Handle to the database
     */
    public static EntityManager getDb() {  return tloc.get();  }

    /**
     * Get an object ID.
     *
     * @param o Graph object
     * @return Id for this object
     */
    public static long getId( Object o )
    {
        //return cache.ext().getObjectInfo(o).getUUID();
        return 0L;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Mutators">
    /** {@inheritDoc}
     */
    @Override
    public String getPersistUnit() {  return persistUnit;  }

    /** {@inheritDoc}
     */
    @Override
    public String getDbURL() {  return dbURL;  }

    /** {@inheritDoc}
     */
    @Override
    public String getName() {  return name;  }

    /** {@inheritDoc}
     */
    @Override
    public List<GraphVertex> getAllVertices()
    {
        TypedQuery<GraphVertex> query =
          getDb().createNamedQuery("Vertex.findAll", GraphVertex.class);

        List<GraphVertex> lgv = query.getResultList();
        if( lgv == null )
            {  return new ArrayList<>();  }
        else
            {  return lgv;  }
    }

    /** {@inheritDoc}
     */
    @Override
    public Collection<GraphVertex> getAllVerticesDetached()
    {
        TypedQuery<GraphVertex> query =
          getDb().createNamedQuery("Vertex.findAll", GraphVertex.class);

        List<GraphVertex> lgv = query.getResultList();
        if( lgv == null )
            {  return new ArrayList<>();  }
        else
            {
                Collection<GraphVertex> lgc = new ArrayList<>();
                lgv.stream().map((gv) -> {
                    getDb().detach( gv );
                return gv;
            }).forEachOrdered((gv) -> {
                lgc.add( gv );
            });
                return lgc;
            }
    }

    /** {@inheritDoc}
     */
    @Override
    public List<GraphEdge> getAllEdges()
    {
        TypedQuery<GraphEdge> query =
          getDb().createNamedQuery("Edge.findAll", GraphEdge.class);

        List<GraphEdge> lge = query.getResultList();
        if( lge == null )
            {  return new ArrayList<>();  }
        else
            {  return lge;  }
    }

    /** {@inheritDoc}
     */
    @Override
    public List<Edge> getEdges( Edge e ) { throw new UnsupportedOperationException(); }

    /** {@inheritDoc}
     */
    @Override
    public List<Vertex> getVertices( Vertex v ) { throw new UnsupportedOperationException(); }

    /** {@inheritDoc}
     */
    @Override
    public long getNumberOfObjects( Class c )
    {
        /*
         * How it should be done (but Versant 7/8? doesn't have count())
         *
        CriteriaBuilder cb = getDb().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        cq.select(cb.count(cq.from(c)));
        return getDb().createQuery(cq).getSingleResult();

        The Versant method: Get all, instantiate and count...
         */
//        Query q = getDb().createQuery("select o from " +c.getSimpleName()+ " o" );
//        return q.getResultList().size();
        
        // If ObjectDb...
        CriteriaBuilder cb = getDb().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        cq.select(cb.count(cq.from(c)));
        return getDb().createQuery(cq).getSingleResult();
    }

    /**{@inheritDoc}
     */
    @Override
    public long addVertex( Vertex v )
    {
        getDb().persist( v );
        return 0L;
    }

    /** {@inheritDoc}
     */
    @Override
    public Vertex getVertex( long v )
            throws NoSuchEntityWithIdException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /** {@inheritDoc}
     */
    @Override
    public Vertex getEdge( long v )
            throws NoSuchEntityWithIdException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /** {@inheritDoc}
     */
    @Override
    public void deleteVertex( Vertex v )
    {
         throw new UnsupportedOperationException("Not supported yet.");
    }

    /** {@inheritDoc}
     */
    @Override
    public long addEdge( Vertex from, Vertex to )
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /** {@inheritDoc}
     */
    @Override
    public long addEdge( Vertex from, Vertex to, Weight weight )
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /** {@inheritDoc}
     */
    @Override
    public List<Edge> getEdges( long v, long w )
            throws NoConnectingEdgeException
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteEdge( Edge e )
    {
        // Delete vertex references
        e.delEdge();
        // Delete on db
        //cache.delete( e );
        // Yes, we're certain
        //cache.commit();
    }

    /** {@inheritDoc}
     */
    @Override
    public long getCount() {  return 0;  }

    /** {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {  return getCount() == 0;  }

    /** {@inheritDoc}
     */
    @Override
    public void purge()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Create an Adjacency List.
     * Works for Directed and Undirected graphs.
     *
     * @param gr the complete graph
     * @param cv filter by given vertex class
     * @param ce filter by given edge class
     * @return The adjacency matrix as an Iterable of directly connecting
     * vertices (connected with an <i>outgoing</i> edge for directed graphs) for
     * each vertex in the graph
     */
    public static Map<Vertex,Iterable<Vertex>> adjacencyList( Graph gr, Class cv, Class ce )
    {
      Map<Vertex,Iterable<Vertex>> adjList = new HashMap<>();

        // Get all vertices
        gr.getAllVertices().forEach((v) -> {
            adjList.put( v, v.getSuccessors(cv,ce) );
        });

        return adjList;
    }

    // Return Iterator of contained objects
    @Override
    public Iterable getIterator()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Traversals">
    /** {@inheritDoc}
     */
    @Override
    public void accept( Strategy visitor )
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /** {@inheritDoc}
     */
    @Override
    public void depthFirstTraversal( PrePostStrategy visitor, Vertex vstart )
    {
      List visited = new ArrayList();  // No type as we're adding ints

        depthFirstTraversal( visitor, vstart, visited );
    }

    /*
     * Recursive method for public method
     */
    private void depthFirstTraversal( PrePostStrategy visitor, Vertex v, List visited )
    {
	if( visitor.isDone() )
            {  return;  }

	visitor.preVisit( v );
	visited.add( v.getId() );  // Although auto-boxing is pretty slow...

	for( Vertex to : v.getSuccessors(null,null) )
	{
            // No mapping?  *** Would RandomAccess prove better?  Or a Map<>? ***
	    if( visited.indexOf(to.getId()) == -1 )
                {  depthFirstTraversal( visitor, to, visited );  }
	}

	visitor.postVisit (v);
    }

    /** {@inheritDoc}
     */
    @Override
    public void breadthFirstTraversal( Strategy visitor, Vertex vstart )
    {
      List visited = new ArrayList();
      Queue<Vertex>  queue = new LinkedList<>();

	visited.add( vstart.getId() );
	queue.add( vstart );

	while( !queue.isEmpty () && !visitor.isDone( ))
	{
	    Vertex v = queue.remove();
	    visitor.visit( v );

            for( Vertex to : v.getSuccessors(null,null) )
	    {
		if( visited.indexOf(to.getId()) == -1 )
		{
		    visited.add( to.getId() );
		    queue.add( to );
		}
	    }
	}
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Comparator">
    /**
     * If containers have the same number of items, consider them equal
     * @param c
     * @return 
     */
    @Override
    public int compareTo( Container c )
    {
        return this.getCount()<c.getCount() ? Const.BEFORE : this.getCount()==c.getCount()? Const.EQUAL : Const.AFTER;
    }
    //</editor-fold>
}