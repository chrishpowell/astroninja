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
package eu.discoveri.astroninja.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import eu.discoveri.astroninja.edge.Edge;
import eu.discoveri.astroninja.edge.GraphEdge;
import eu.discoveri.astroninja.graph.Graph;
import eu.discoveri.astroninja.graph.GraphI;
import eu.discoveri.astroninja.vertex.Vertex;
import eu.discoveri.astroninja.vertex.GraphVertex;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Graph database utilities:
 * <pre>
 *      . Named Queries
 *      . Edge / Vertex, class list
 *      . Dump, Show vertices and their edges (graph dump) and other information
 *      . Adjaceny list
 *      . Structure, Graph object graph - tbd @todo graph object dump etc.
 * </pre>
 *
 * @author Chris Powell, CPgraph Ltd.
 * @version 0.8
 *
 * @since 0.9
 */
public class DatabaseUtils
{
    //<editor-fold defaultstate="collapsed" desc="attributes">
    // JSON for Vertexes
    private static StringBuffer outVs = null;
    // JSON for Edges
    private static StringBuffer outEs = null;
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Named Queries">
    /**
     * Find Named Queries of a class by key.
     *
     * @param clazz class containing named query
     * @param namedQueryKey named query key
     * @return Named query
     */
    public String getNamedQueryCode( String namedQueryKey, Class<? extends Object> clazz )
    {
        NamedQueries namedQueriesAnnotation = clazz.getAnnotation(NamedQueries.class);
        NamedQuery[] namedQueryAnnotations = namedQueriesAnnotation.value();

        String code = null;
        for( NamedQuery namedQuery : namedQueryAnnotations )
        {
            if( namedQuery.name().equals(namedQueryKey) )
            {
                code = namedQuery.query();
                break;
            }
        }

        /* Frikkin VJPA doesn't have MappedSuperClass....
         * if (code == null)
         * {
         * if (clazz.getSuperclass().getAnnotation(MappedSuperclass.class) != null)
         * {
         * code = getNamedQueryCode(clazz.getSuperclass(), namedQueryKey);
         * }
         * }
         * */

        return code;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Vertex and Edge lists">
    /**
     * Sort and de-dup Vertex class names list.
     *
     * @param vs list of vertices
     * @return A sorted set of vertex types
     */
    public static SortedSet<String> sortVertexList( List<GraphVertex> vs )
    {
        List<String> vsn = new ArrayList<>();

        // Get the list of vertex class names
        for( Vertex v : vs )
        {
            vsn.add( v.getClass().getName() );
        }

        // Remove duplicates (best way?)
        SortedSet<String> vss = new TreeSet<>(vsn);
        return vss;
    }

    /**
     * Sort and de-dup Edge class names list.
     *
     * @param es list of edges
     * @return A sorted set of edge types
     */
    public static SortedSet<String> sortEdgeList( List<GraphEdge> es )
    {
        List<String> esn = new ArrayList<>();

        // Get the list of vertex class names
        for( Edge e : es )
        {
            esn.add( e.getClass().getName() );
        }

        // Remove duplicates (best way?)
        SortedSet<String> ess = new TreeSet<>(esn);
        return ess;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Adjacency List utils">
    /**
     * Dump the adjacency list for graph with given vertex type and edge type.
     * @param gr the graph
     * @param cv the vertex class (eg: Person)
     * @param ce the edge class (eg: Follows)
     */
    public static void adjListDump( Graph gr, Class cv, Class ce )
    {
        // Get the adjacency list for a graph
        Map<Vertex,Iterable<Vertex>> adjList = GraphI.adjacencyList( gr, cv,ce );
        for( Map.Entry<Vertex,Iterable<Vertex>>e : adjList.entrySet() )
        {
            System.out.print(e.getKey().getVName() +"> " );
            for( Vertex v :e.getValue() )
            {
                System.out.print( "[" +v.getVName()+ "] " );
            }
            System.out.println("");
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Collection from Iterable">
    /**
     * Make a Collection from Iterable.
     * @param <E>  Collection type, eg: List
     * @param iter The iterable to make concrete
     * @return The instantiated collection from an iterable
     */
    public static <E> Collection<E> makeCollection( Iterable<E> iter )
    {
        Collection<E> list = new ArrayList<E>();

        for( E item : iter ) { list.add(item); }
        return list;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="EntityMgr inspect">
    /**
     * Inspect/dump the EntityManager details
     *
     * @param em
     */
    public static void inspectEM( EntityManager em, boolean properties, boolean metamodel )
    {
        System.out.println("EntityManager\r\n-------------");
        if( !em.isOpen() )
        {
            System.out.println("Not open\r\n-------------");
            return;
        }
        
        System.out.println(" Is open? Yes");
        
        if( properties )
        {
            System.out.println("  Properties>");
            em.getProperties().forEach((key,value)->System.out.println("   Key: [" +key+ "], Value: [" +value+ "]"));
        }
        
        if( metamodel )
        {
            System.out.println("  Metamodel>");
            em.getMetamodel().getEntities().forEach((e)->System.out.println("   EntityType: " +e.getName()));
        }
        
        System.out.println("-------------");
    }
    
    public static void inspectEM( EntityManager em )
    {
        inspectEM( em, true, true );
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="JSON output">
    /**
     * Get Vertexes, JSON output (populated after outputGraphJSON call)
     * @return
     */
    public static StringBuffer getJSONVs() { return outVs; }
    /**
     * Get Vertexes, JSON output (populated after outputGraphJSON call)
     * @return
     */
    public static StringBuffer getJSONEs() { return outEs; }
    
    /**
     * Dump graph as JSON.
     *
     * @param gr
     */
    public static void dumpGraphJSON( Graph gr )
    {
        // Generate JSON
        outputGraphJSON( gr );
        
        // Print them off
        System.out.println("Vertexes>");
        System.out.println( getJSONVs().toString() );
        System.out.println("Edges>");
        System.out.println( getJSONEs().toString() );
    }
    
    /**
     * Build a JSON output string.  Exceptions are logged.
     * @TODO:  Check if we need 1. More than 2Gb ever, 2. Do we start with a
     * bigger buffer
     *
     * @param gr
     */
    public static void outputGraphJSON( Graph gr )
    {
        // JSON for Vertexes
        outVs = new StringBuffer(4096);     // Can go near to 2G
        // JSON for Edges
        outEs = new StringBuffer(4096);     // Can go near to 2G
        
        // Get all edges
        List<GraphEdge> es = gr.getAllEdges();
        // Get all vertices
        List<GraphVertex> vs = gr.getAllVertices();
        
        //... Construct the JSON
        // Vertexes
        vs.forEach((v) -> {
            try {
                outVs.append(v.toJSON()).append(',');
            } catch (JsonProcessingException ex) {
                Logger.getLogger(DatabaseUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        // Remove last comma
        outVs.deleteCharAt(outVs.length()-1);
        
        // Edges
        es.forEach((e) -> { outEs.append(e.toJSONVNameOnly()).append(','); });
        // Remove last comma
        outEs.deleteCharAt(outEs.length()-1);
    }
    
    /**
     * Write Graph output JSON to a file.
     *
     * @param gr
     * @param file
     * @throws IOException
     */
    public static void writeJSONtoFile( Graph gr, File file )
            throws IOException
    {
        // Generat ethe JSON
        outputGraphJSON( gr );
        
        // Create (new) file
        file.createNewFile();
        
        // Write JSON out
        try
            (   // Setup write to file
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)))
            )
            {
                // Write JSON out
                out.write( "{\"vertexes\":[" +outVs+ "]," );
                out.write( "\"links\":[" +outEs+ "]}" );

                // Flush (& close)
                out.flush();
            }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Database dump">
    /**
     * Dump the graph.
     * @param gr the graph
     */
    public static void dumpGraph( Graph gr )
    {
        // Get all edges
        List<GraphEdge> es = gr.getAllEdges();
        // Get all vertices
        List<GraphVertex> vs = gr.getAllVertices();

        // Dump the vertex class list
        System.out.println("Vertex classes (tot. " +vs.size()+ ")\r\n-----------------------");
        sortVertexList(vs).forEach((str) -> {
            long objCount = 0;

            try
            {
                objCount = gr.getNumberOfObjects(Class.forName(str));
            }
            catch( ClassNotFoundException cnex )
            {
                System.out.println( "Error, name not found: " +str );
            }
            System.out.println("  " +str+ " (" +objCount+ ")" );
        });
        System.out.println("-----------------------\r\n");

        // Dump the edge class list
        System.out.println("Edge classes (tot. " +es.size()+ ")\r\n---------------------");
        sortEdgeList(es).forEach((str) -> {
            long objCount = 0;

            try
            {
                objCount = gr.getNumberOfObjects(Class.forName(str));
            }
            catch( ClassNotFoundException cnex )
            {
                System.out.println( "Error, name not found: " +str );
            }
            System.out.println("  " +str+ "\t (" +objCount+ ")" );
        });
        System.out.println("---------------------\r\n");

        /*
         * Dump vertices
         */
        System.out.println("\r\nDump of vertices (and their edges)\r\n----------------------------------");
        vs.stream().map((v) -> {
            // Vertex
            System.out.println( "> [" +v.getClass().getSimpleName()+ " UUID(" +v.getId()+ ")] '" +v.toString()+ "', created: "  +v.getCreateDate() );
            return v;
        }).map((v) -> {
            // Outgoing edges of this vertex
            System.out.println("  Outgoing edges:");
            return v;
        }).map((v) -> {
            for( Edge e : v.getEmanatingEdges(null, null) )
            {
                Vertex v1 = e.getV1();
                System.out.println( "    [" +e.getClass().getSimpleName()+ " UUID(" +e.getId()+ ")] Far vertex:[" +v1.getClass().getSimpleName()+ " UUID(" +v1.getId()+ ")] '" +v1.toString()+ "'" );
                System.out.print( "      Dir edge: " +e.isDirected() );
                if( e.getQualifier() != null )
                    System.out.println( ", qualifier UUID(" +e.getId()+ ") '" +e.getQualifier().toString()+ "'" );
                else
                    System.out.println("");
                if( e.getWeight() != null )
                    System.out.println( "    Weight: " +e.getWeight().getWeight().toString() );
            }
            return v;
        }).map((v) -> {
            // Incoming edges of this vertex
            System.out.println("  Incoming edges:");
            return v;
        }).forEachOrdered((v) -> {
            for( Edge e : v.getIncidentEdges(null, null) )
            {
                Vertex v0 = e.getV0();
                System.out.println( "    [" +e.getClass().getSimpleName()+ " UUID(" +e.getId()+ ")] Far vertex:[" +v0.getClass().getSimpleName()+ " UUID(" +v0.getId()+ ")] '" +v0.toString()+ "'" );
                System.out.print( "      Dir edge: " +e.isDirected() );
                if( e.getQualifier() != null )
                    System.out.println( ", qualifier UUID(" +e.getId()+ ") '" +e.getQualifier().toString()+ "'" );
                else
                    System.out.println("");
                if( e.getWeight() != null )
                    System.out.println( "    Weight: " +e.getWeight().getWeight().toString() );
            }
        });
    }
    //</editor-fold>
}
