/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 *
 */
package eu.discoveri.astroninja.example.main;

//import eu.discoveri.astroninja.example.entity.AccState;
import com.fasterxml.jackson.core.JsonProcessingException;
import eu.discoveri.astroninja.example.edge.Follows;
import eu.discoveri.astroninja.example.entity.Phone;
import eu.discoveri.astroninja.example.vertex.PersonImpl;
import eu.discoveri.astroninja.exception.NullVertexException;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class SimpleJSONOutput
{
    public static void main(String[] args)
            throws JsonProcessingException
    {
        // 'Start' node
        PersonImpl p1 = null;
        
        // Build a test graph
        try
        {
            // Phones, emails, addresses
            Phone ph1 = new Phone( "Work-1", "LAND","+44 (0)203 123 4567 x89");
            Phone ph2 = new Phone( "Mob-1", "MOB","+44 (0)794 123 4567");
            List<Phone> phl1 = new ArrayList<>();
            phl1.add(ph1); phl1.add(ph2);

            // People
            p1 = new PersonImpl( "Chris", "Powell", "crispy" );
            p1.setPhones(phl1);
            p1.setDefRadiusDT(10).setFill("steelblue").setStroke("blue");
            PersonImpl p2 = new PersonImpl( "Harry", "Houdini", "houdin" );
            PersonImpl p3 = new PersonImpl( "Einstein", "Albert", "ae" );
            PersonImpl p4 = new PersonImpl( "Adi", "Andrei", "aa" );
            PersonImpl p5 = new PersonImpl( "Adrian", "Powell", "ap" );
            PersonImpl p6 = new PersonImpl( "Long", "Silver", "John", "", "ap" );

               // Connect people
               try
               {
                   Follows f1 = new Follows( p1, p2 );
                   Follows f2 = new Follows( p3, p1 );
                   Follows f3 = new Follows( p3, p2 );
               }
               catch( NullVertexException nvx )
               {
                   System.out.println( "Bad vertex! ");
                   System.exit( -1 );
               }
        }
        catch( Exception ex )
        {
            ex.printStackTrace();
        }
        
        System.out.println( "JSON: " +p1.toJSON() );
    }
}
