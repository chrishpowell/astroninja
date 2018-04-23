/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.test.throwaway;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class Main
{
    public static void main(String[] args)
    {
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("objectdb://localhost/test.tmp;user=admin;password=admin;drop");
        EntityManager em = emf.createEntityManager();

        // Start transaction
        em.getTransaction().begin();
        
        Name name = new Name("Chris", "Powell");
        Useren user = new Useren(name, "chrisp@discoveri.com");
        em.persist(user);
        
        em.getTransaction().commit();

        // Finish
        em.close();
        emf.close();
    }
}
