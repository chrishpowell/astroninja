package eu.discoveri.astroninja.example.main;

import javax.persistence.*;

/**
 * Test Case
 */
public class MyTestCase
{
    public static void main(String[] args) 
    {
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("Test_PU");  // "objectdb://localhost/test.tmp;user=admin;password=admin;drop"
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        MyEntity e1 = new MyEntity("test1");
        em.persist(e1);
        em.getTransaction().commit();

        em.getMetamodel().getEntities().forEach((e) ->
                System.out.println("   EntityType: " +e.getName()));

        em.close();
        emf.close();
    }
}
