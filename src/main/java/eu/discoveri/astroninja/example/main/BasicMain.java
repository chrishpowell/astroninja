/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 *
 */

package eu.discoveri.astroninja.example.main;

//import eu.discoveri.astroninja.utils.DatabaseUtils;
import eu.discoveri.astroninja.example.entity.Phone;
import javax.persistence.*;
import java.util.*;



/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class BasicMain
{
    public static void main(String[] args)
    {
        // Open a database connection
        // (create a new database if it doesn't exist yet):
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("astrotest_PU");
        
        EntityManager em = emf.createEntityManager();

        // Store 1000 Point objects in the database:
        em.getTransaction().begin();
        for (int i = 0; i < 10; i++)
        {
            Point p = new Point(i, i);
            em.persist(p);
        }
        
        Phone ph1 = new Phone( "Work-1", "LAND","+44 (0)203 123 4567 x89");
        em.persist(ph1);
        em.getTransaction().commit();

        // Find the number of Point objects in the database:
        Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
        System.out.println("Total Points: " + q1.getSingleResult());

        // Find the average X value:
        Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");
        System.out.println("Average X: " + q2.getSingleResult());

        // Retrieve all the Point objects from the database:
        TypedQuery<Point> query =
            em.createQuery("SELECT p FROM Point p", Point.class);
        List<Point> results = query.getResultList();
        results.forEach((p) -> {
            System.out.println(p);
        });

        // Close the database connection:
        em.close();
        emf.close();
    }
}
