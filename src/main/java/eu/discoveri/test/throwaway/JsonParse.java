/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.test.throwaway;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;


/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class JsonParse
{
    public static void main(String[] args)
            throws Exception
    {
        // JSON
        StringBuilder json = new StringBuilder();
        // Open file
        File jsonTest = new File("/home/chrispowell/NetBeansProjects/AstroNinja/src/main/java/eu/discoveri/test/throwaway/jsontest.json");
                
        // Map objects
        ObjectMapper objm = new ObjectMapper();
        
        // Map straight to object
        Root root = objm.readValue(jsonTest,Root.class);
        
        // Show some data
        System.out.println( "obj1:attr1: " +root.getData().getObj1().getAttr1() );
        root.getData().getObj1().getList1().forEach(list1->{
            System.out.println("obj1:list1: " +list1);
        });
        
        System.out.println( "\nobj2:attr2: " +root.getData().getObj2().getAttr2() );
        root.getData().getObj2().getList2().forEach(list2->{
            list2.getNs21().forEach(ns21->{
                System.out.println("obj2:list2:ns21: " +ns21);
            });
        });
    }
}
