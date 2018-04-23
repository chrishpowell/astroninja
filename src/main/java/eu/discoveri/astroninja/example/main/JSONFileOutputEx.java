/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 *
 */

package eu.discoveri.astroninja.example.main;

//import eu.discoveri.astroninja.example.entity.AccState;
import eu.discoveri.astroninja.example.edge.Follows;
import eu.discoveri.astroninja.example.entity.Phone;
import eu.discoveri.astroninja.example.vertex.PersonImpl;
import eu.discoveri.astroninja.exception.NullVertexException;
import eu.discoveri.astroninja.graph.GraphI;
import eu.discoveri.astroninja.utils.DatabaseUtils;
import java.io.File;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class JSONFileOutputEx
{
    public static void main(String[] args)
            throws Exception
    {
        // Output JSON file
        String JSONFILE = "/home/chrispowell/NetBeansProjects/AstroNinja/src/main/java/eu/discoveri/astroninja/html/network.json";
        // 'Start' node
        PersonImpl p1 = null;
        
        // Graph db setup. Don't declare Graphi/EM as static as they're thread local
        String  graphName = "TestAstro";
        GraphI gr = new GraphI( graphName );
        EntityManager db = gr.openGraph();

        System.out.println( "\r\nOpened GraphDB... Name: " +graphName+ ", PU: " +gr.getPersistUnit() );
        
        // Build a test graph
        try
        {
            db.getTransaction().begin();

            // Phones, emails, addresses
            Phone ph1 = new Phone( "Work-1", "LAND","+44 (0)203 123 4567 x89");
            db.persist(ph1);
            Phone ph2 = new Phone( "Mob-1", "MOB","+44 (0)794 123 4567");
            db.persist(ph2);
            List<Phone> phl1 = new ArrayList<>();
            phl1.add(ph1); phl1.add(ph2);

            // People
            p1 = new PersonImpl( "Chris", "Powell", "crispy" );
            p1.setPhones(phl1);
            p1.setDefRadiusDT(10).setFill("steelblue").setStroke("blue");
            db.persist( p1 );
            PersonImpl p2 = new PersonImpl( "Harry", "Houdini", "houdin" );
            db.persist( p2 );
            PersonImpl p3 = new PersonImpl( "Einstein", "Albert", "ae" );
            db.persist( p3 );
            PersonImpl p4 = new PersonImpl( "Adi", "Andrei", "aa" );
            db.persist( p4 );
            PersonImpl p5 = new PersonImpl( "Adrian", "Powell", "ap" );
            db.persist( p5 );
            PersonImpl p6 = new PersonImpl( "Long", "Silver", "John", "", "ap" );
//            p6.setAccState(AccState.BLOCKED);
            db.persist( p6 );

               // Connect people
               try
               {
                   Follows f1 = new Follows( p1, p2 );
                   db.persist( f1 );
                   Follows f2 = new Follows( p3, p1 );
                   db.persist( f2 );
                   Follows f3 = new Follows( p3, p2 );
                   db.persist( f3 );
//                   Follows f4 = new Follows( p2, p1, t1 );
//                   db.persist( f4 );
               }
               catch( NullVertexException nvx )
               {
                   System.out.println( "Bad vertex! ");
                   System.exit( -1 );
               }

            db.getTransaction().commit();
        }
        catch( Exception ex )
        {
            ex.printStackTrace();
        }
        finally
        {
            if( db.getTransaction().isActive() )
               { System.out.println("Some problem... rolling back"); db.getTransaction().rollback();  }
        }
        
                // Find the number of Point & Phone objects in the database:
        Query q1 = db.createQuery("SELECT COUNT(p) FROM PersonImpl p");
        System.out.println("Total People: " + q1.getSingleResult());
        
        Query q2 = db.createQuery("SELECT COUNT(p) FROM Phone p");
        System.out.println("Total Phones: " + q2.getSingleResult());

        // What have we got?
        DatabaseUtils.dumpGraph(gr);
        
        // Output graph
        DatabaseUtils.writeJSONtoFile(gr,new File("/home/chrispowell/Documents/AstroTurf/Examples/graphDump.json"));

        // Close
        gr.closeGraph();
    }
}
