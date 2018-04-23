/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */
package eu.discoveri.astroninja.astronomy;

import java.io.File;
import java.util.List;


/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class BuildConstellations
{   
    /**
     * M A I N
     * =======
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args)
            throws Exception
    {
        final String CSVHYGFILE = "/home/chrispowell/Documents/AstroTurf/SAC/HYG-Database-master/hygdata_v3.csv";
        final String CSVSACFILE = "/home/chrispowell/Documents/AstroTurf/SAC/HYG-Database-master/sac.csv";
        final String MANTOPOFILE = "/home/chrispowell/Documents/AstroTurf/OL/topoJSON/ConstellsManual.json";
        final String CSVTOPOFILE = "/home/chrispowell/Documents/AstroTurf/OL/topoJSON/ConstellsBig.json";
        
        /*
         * Build test 'constellations'
         */
//        System.out.println("Build some test constellations...");
//        List<Constellation> testConstellations = ConstellationProjectBuilder.buildManuallyTest();
//        System.out.println("Write manual topoJsonFile...");
//        ConstellationProjectBuilder.writeTopoJson( testConstellations, new File(MANTOPOFILE), false );

        /*
         * Build some constellations manually
         */
        System.out.println("Build some constellations...");
        List<Constellation> manConstellations = ConstellationProjectBuilder.buildManuallyHYG();
        
        // Dump the detail
//        System.out.println("Dump constellations [" +manConstellations.size()+ "]");
//        manConstellations.forEach( mc->{mc.dump();} );

        /*
         * Write out the topoJson
         */
        System.out.println("Write manual topoJsonFile...");
        ConstellationProjectBuilder.writeTopoJson( manConstellations, new File(MANTOPOFILE), false );
        
        /*
         * Build constellations from CSV
         */
//        System.out.println("Build 88? constellations...");
//        List<Constellation> bigConstellations = ConstellationProjectBuilder.buildFromCSVHYG(new File(CSVHYGFILE),4.5f);
        
        // Add drawn links between stars
        // TBD
        
        // Add constellation borders
        // TBD
                
        // Dump the detail
//        System.out.println("Dump constellations [" +bigConstellations.size()+ "]");
//        bigConstellations.forEach( bc->{bc.dump();} );
        
//        System.out.println("Write big topoJsonFile...");
//        ConstellationProjectBuilder.writeTopoJson( bigConstellations, new File(CSVTOPOFILE), true );
    }
}