/*
 * Copyright 2014 Chris Powell, CPgraph Ltd.
 * http://CPgraph.com
 */
package eu.discoveri.astroninja.utils;

import java.lang.annotation.Annotation;


/**
 * A utility to trace annotations (including RUNTIME annotations).  Useful, for
 * example, to check your persisted class has an @Id (to enable persistence).
 *
 * @author Chris Powell, CPgraph Ltd.
 */
public class AnnotationList
{
    /**
     * Print the hierarchical annotation list for given class
     *
     * @param clazz
     */
    public static void dumpAll( Class<? extends Object> clazz )
    {
        // Interfaces
        System.out.println("Interfaces\r\ns---------");
        System.out.println( "> " +clazz.getCanonicalName()+ " interfaces" );
        Class<?>[] ifs = clazz.getInterfaces();
        for( Class<?> c : ifs )
        {
            System.out.println( "  > " +c.getName() );
        }

        // This class and superclasses
        System.out.println("Annotations\r\n-----------");
        System.out.println( "> " +clazz.getCanonicalName()+ " (and superclasses)" );
        while( clazz != null )
        {
            Annotation[] anno = clazz.getAnnotations();
            System.out.println( "   >" +clazz.getName() );
            for( Annotation a : anno )
            {
                System.out.println("     >> " +a.toString() );
            }
            clazz = clazz.getSuperclass();
        }
    }
}
