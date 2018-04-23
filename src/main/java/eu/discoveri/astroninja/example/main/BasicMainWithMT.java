/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 *
 */

package eu.discoveri.astroninja.example.main;

//import eu.discoveri.astroninja.utils.DatabaseUtils;
import eu.discoveri.astroninja.example.entity.Phone;
import eu.discoveri.astroninja.utils.DatabaseUtils;
import javax.persistence.*;
import java.util.*;



/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class BasicMainWithMT
{
    public static void main(String[] args)
    {
        GraphEM gem = new GraphEM();
        EntityManager em = gem.openGraph();
        
        // Store 1000 Point objects in the database:
        em.getTransaction().begin();
        for (int i = 0; i < 10; i++)
        {
            Point p = new Point(i, i);
            em.persist(p);
        }
        
        Phone ph1 = new Phone( "Work-1", "LAND","+44 (0)203 123 4567 x89");
        em.persist(ph1);
        Phone ph2 = new Phone( "Home-2", "LAND","+44 (0)203 123 4567 x89");
        em.persist(ph2);
        em.getTransaction().commit();

        // Find the number of Point & Phone objects in the database:
        Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
        System.out.println("Total Points: " + q1.getSingleResult());
        
        Query q2 = em.createQuery("SELECT COUNT(p) FROM Phone p");
        System.out.println("Total Phones: " + q2.getSingleResult());

        // Close the database connection:
        em.close();
        gem.getEmf().close();
    }
}

class GraphEM
{
    // Entity Manager factory
    private final EntityManagerFactory              emf;
    // Reference/handle to database
    private static final ThreadLocal<EntityManager> tloc = new ThreadLocal<>();
    
    public GraphEM()
    {
        emf = Persistence.createEntityManagerFactory("astrotest_PU");
    }
    
    public synchronized EntityManager openGraph()
    {
        EntityManager cache = tloc.get();
        if( cache == null )
        {
            cache = emf.createEntityManager();
            tloc.set( cache );
        }

        return cache;
    }
    
    public EntityManagerFactory getEmf()
    {
        return emf;
    }
}
