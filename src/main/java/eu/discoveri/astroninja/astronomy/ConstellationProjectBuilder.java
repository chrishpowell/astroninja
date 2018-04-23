/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.astronomy;

//import com.jhlabs.map.proj.GnomonicAzimuthalProjection;
import eu.discoveri.astroninja.exception.DuplicateStarException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.CsvToBeanFilter;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.MappingStrategy;


/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class ConstellationProjectBuilder
{
    /**
     * Build some constellations manually.
     * 
     * @return List of constellations
     * @throws DuplicateStarException 
     */
    public static List<Constellation> buildManuallyTest()
            throws DuplicateStarException
    {
        // A list of constellations
        List<Constellation> constellations = new ArrayList<>();
        
        /*
         * Test constellation
         *   (four stars like a scythe with a left hook and downward handle)
         */
        Constellation lhook = new Constellation("lhook");
        constellations.add(lhook);
        
        // Main star list
        ConstlStar No1 = lhook.addStar(new ConstlStarHYG("No1","Alpha",0.f,60.f,10.f,2.15f));
        ConstlStar No2 = lhook.addStar(new ConstlStarHYG("No2","Beta",1.f,45.f,10.f,2.28f));
        ConstlStar No3 = lhook.addStar(new ConstlStarHYG("No3","Gamma",0.f,30.f,10.f,2.15f));
        ConstlStar No4 = lhook.addStar(new ConstlStarHYG("No4","Delta",0.f,10.f,10.f,2.66f));
        
        return constellations;
    }
    
    /**
     * Build some constellations manually.
     * 
     * @return List of constellations
     * @throws DuplicateStarException 
     */
    public static List<Constellation> buildManuallyHYG()
            throws DuplicateStarException
    {
        // A list of constellations
        List<Constellation> constellations = new ArrayList<>();
        
        // Deneb test
//        System.out.println("Cygnus>");
        
//        AbstractConstlStar deneAbstractConstlStarnstlStar("Deneb","Cygnus",20.690655554,45.2803,0.0,0.0);
//        System.out.println(" Using Gnomonic:");
//        System.out.println("  " +deneb.getName()+ ", x: " +deneb.getProjection1().getX()+ ", y: " +deneb.getProjection1().getY());
//        System.out.println(" Using C2DP:");
//        System.out.println("  " +deneb.getName()+ ", x: " +deneb.getProjection2().getX()+ ", y: " +deneb.getProjection2().getY());
//        System.out.println(" Using C2DP1:");
//        System.out.println("  " +deneb.getName()+ ", x: " +deneb.getProjection3().getX()+ ", y: " +deneb.getProjection3().getY());
        
        /*
         * Build 4 constellations (CAS,TRI,ORI,UMA)
         */
        //... Generate main points for CASSIOPEIA
        Constellation cas = new Constellation("Cassiopeia");
        constellations.add(cas);  // Add to list
        
        // Main star list
        ConstlStar casShedir = cas.addStar(new ConstlStarHYG("Shedir","18Alp Cas",0.675116f,56.537f,168.3502f,2.15f));
        ConstlStar casCaph = cas.addStar(new ConstlStarHYG("Caph","11Bet Cas",0.152887f,59.14978f,16.7842f,2.28f));
        ConstlStar casCih = cas.addStar(new ConstlStarHYG("Cih","27Gam Cas",0.945143f,60.71674f,168.3502f,2.15f));
        ConstlStar casRuchbah = cas.addStar(new ConstlStarHYG("Ruchbah","37Del Cas",1.430216f,60.235283f,30.4785f,2.66f));
        ConstlStar casEpsilon = cas.addStar(new ConstlStarHYG("Epsilon",1.906584f,63.670101f,126.2626f,3.35f));
        
        // Add links for drawing constellation
        cas.linkStars(casEpsilon,casRuchbah);
        cas.linkStars(casRuchbah,casCih);
        cas.linkStars(casCih,casShedir);
        cas.linkStars(casShedir,casCaph);
        
        // Add boundary
        // TBD
        
        
        //... TRIANGULUM
        Constellation tri = new Constellation("Triangulum");
        constellations.add(tri);  // Add to list
        
        // Main star list
        ConstlStar triBeta = tri.addStar(new ConstlStarHYG("Beta",2.159058f,34.987297f,38.8954f,3.0f));
        ConstlStar triAlpha = tri.addStar(new ConstlStarHYG("Alpha",1.884696f,29.578829f,19.4175f,3.42f));
        ConstlStar triGamma = tri.addStar(new ConstlStarHYG("Gamma",2.288573f,33.847194f,34.4353f,4.03f));
        
        // Add links for drawing constellation
        tri.linkStars(triAlpha,triBeta);
        tri.linkStars(triAlpha,triGamma);
        tri.linkStars(triGamma,triBeta);

        // Add boundary
        // TBD
        
        
        //... ORION
        Constellation ori = new Constellation("Orion");
        constellations.add(ori);  // Add to list
        
       // Main star list
        ConstlStar oriRigel = ori.addStar(new ConstlStarHYG("Rigel",5.242298f,-8.20164f,264.5503f,0.18f));
        ConstlStar oriBetelguese = ori.addStar(new ConstlStarHYG("Betelguese",5.919529f,7.407063f,152.6718f,0.45f));
        ConstlStar oriBellatrix = ori.addStar(new ConstlStarHYG("Bellatrix",5.418851f,6.349702f,77.3994f,1.64f));
        ConstlStar oriAlnilam = ori.addStar(new ConstlStarHYG("Alnilam",5.603559f,-1.20192f,606.0606f,1.69f));
        ConstlStar oriAlnitak = ori.addStar(new ConstlStarHYG("Alnitak",5.679313f,-1.942572f,225.7336f,1.74f));
        ConstlStar oriSaiph = ori.addStar(new ConstlStarHYG("Saiph",5.795941f,-9.669605f,198.4127f,2.07f));
        ConstlStar oriMintaka = ori.addStar(new ConstlStarHYG("Mintaka",5.533445f,-0.299092f,212.3142f,2.25f));
        ConstlStar oriHatsya = ori.addStar(new ConstlStarHYG("Hatsya",5.590551f,-5.909901f,714.2857f,2.75f));
        ConstlStar ori28Eta = ori.addStar(new ConstlStarHYG("","28Eta",5.407949f,-2.397146f,299.4012f,3.35f));
        
        // Add links for drawing constellation
        ori.linkStars(oriBetelguese,oriBellatrix);
        ori.linkStars(oriBellatrix,oriMintaka);
        ori.linkStars(oriMintaka,ori28Eta);
        ori.linkStars(ori28Eta,oriRigel);
        ori.linkStars(oriMintaka,oriAlnilam);
        ori.linkStars(oriAlnilam,oriAlnitak);
        ori.linkStars(oriAlnitak,oriBetelguese);
        ori.linkStars(oriAlnitak,oriSaiph);
        
        // Add boundary
        // TBD
        
        //... URSA MAJOR
        Constellation uma = new Constellation("Ursa Major");
        constellations.add(uma);  // AbstractConstlStart
        
        // Main star list
        ConstlStar umaAlioth = uma.addStar(new ConstlStarHYG("Alioth",12.900472f,55.959821f,25.31f,1.76f));
        ConstlStar umaDubhe= uma.addStar(new ConstlStarHYG("Dubhe",11.062155f,61.751033f,37.679f,1.81f));
        ConstlStar umaAlkaid = uma.addStar(new ConstlStarHYG("Alkaid",13.792354f,49.313265f,31.8674f,1.85f));
        ConstlStar umaMizar = uma.addStar(new ConstlStarHYG("Mizar",13.398747f,54.925362f,26.3089f,2.23f));
        ConstlStar umaMerak = uma.addStar(new ConstlStarHYG("Merak",11.030677f,56.382427f,24.4499f,2.34f));
        ConstlStar umaPhad = uma.addStar(new ConstlStarHYG("Phad",11.897168f,53.69476f,25.5037f,2.41f));
        ConstlStar umaAlcor = uma.addStar(new ConstlStarHYG("Alcor",13.420413f,54.987958f,25.0564f,3.99f));
        ConstlStar umaMegrez = uma.addStar(new ConstlStarHYG("Megrez",12.257086f,57.032617f,24.6853f,3.32f));
        
        // Add links for drawing constellation
        uma.linkStars(umaAlkaid,umaMizar);
        uma.linkStars(umaMizar,umaAlioth);
        uma.linkStars(umaAlioth,umaMegrez);
        uma.linkStars(umaMegrez,umaDubhe);
        uma.linkStars(umaDubhe,umaMerak);
        uma.linkStars(umaMerak,umaPhad);
        uma.linkStars(umaPhad,umaMegrez);
        
        // Add boundary
        // TBD
        
        return constellations;
    }
    
    
    /**
     * Build constellation list from CSV.
     * 
     * @param hygCSVfile
     * @param maxMagnitude dimmest stars to include
     * @return List of constellations
     * @throws DuplicateStarException
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static List<Constellation> buildFromCSVHYG( File hygCSVfile, float maxMagnitude )
        throws DuplicateStarException, FileNotFoundException, IOException
    {
        // Build list (no guarantee CSV is in Constellation order!
        Map<String,Constellation> mapConstellations = new HashMap<>();
        
        // CSV input filter
        HeaderColumnNameMappingStrategy<ConstlStarHYG> strategy = new HeaderColumnNameMappingStrategy();
        strategy.setType(ConstlStarHYG.class);
        FilterHYGrows filterHYG = new FilterHYGrows(strategy,maxMagnitude);
        
        // Create a dummy constellation (no null deref. warning)
        String constlName = "No such constellation";
        Constellation c = new Constellation(constlName);
        
        /*
         * Read the CSV file
         */
        try( BufferedReader br = new BufferedReader(new FileReader(hygCSVfile)) )
        {
            CsvToBean<ConstlStarHYG> csvToBean = new CsvToBeanBuilder(br)
                    .withOrderedResults(false)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withMappingStrategy(strategy)
                    .withFilter(filterHYG)
                    .build();
            
            // Enter each star up into its constellation
            Iterator<ConstlStarHYG> stars = csvToBean.iterator();
            while( stars.hasNext() )
            {
                ConstlStarHYG hyg = stars.next();
                constlName = hyg.getConstellation();

                // Check if already encountered
                if( mapConstellations.containsKey(constlName) )
                {
                    // Ok, get the Constellation object
                    c = mapConstellations.get(constlName);
                }
                else
                {
                    // No, so create a new one
                    c = new Constellation(constlName);
                    mapConstellations.put(constlName, c);
                }
                
                // Add this star to this constellation
                c.addStar(hyg);
            }
        }
        
        return new ArrayList<>(mapConstellations.values());
    }
    
    
    /**
     * Build constellation list from CSV.
     * 
     * @param sacCSVfile
     * @param maxMagnitude
     * @return
     * @throws DuplicateStarException 
     */
    public static List<Constellation> buildFromCSVSAC( File sacCSVfile, float maxMagnitude )
        throws DuplicateStarException
    {
        
        
        return null;
    }
    
    
    /**
     * Write topoJsonFile.
     * 
     * @param constellations
     * @param wholeUniverse If true, project whole sphere.  Otherwise a
     * calculated bbox will be determined.
     * @param topoJsonFile file to write
     * @throws IOException 
     */
    public static void writeTopoJson( List<Constellation> constellations, File topoJsonFile, boolean wholeUniverse )
            throws IOException
    {
        // Bounding box
        double boxX0 = Float.POSITIVE_INFINITY, boxY0 = Float.POSITIVE_INFINITY,
               boxX1 = Float.NEGATIVE_INFINITY, boxY1 = Float.NEGATIVE_INFINITY;
        // Count the constellations
        int constellationCount = -1;
        // Strings to build, arcs, points, bbox
        StringBuilder bbox = new StringBuilder();
        Formatter bboxFmt = new Formatter(bbox);
        String arcs="", points="", objs="";
        // Repeated use of strings
        final String    COMMA = ",",
                        ARRAYST = "[",
                        ARRAYEND = "],",
                        OBJST = "{",
                        OBJEND = "},";
        
        /*
         * Loop over the constellations building the topoJson.
         * Non-functional as mutable elements.
         *
         * The structure is approx:
         *  Header (topology)
         *  Bounding box
         *  Arcs (array of array), draw each constellation
         *  Objects:
         *      GeometryCollection
         *          Point and attributes for each star
         *          Multiline to connect arcs (draw constellation)
         */
        for( Constellation cons: constellations )
        {
            // String to build, arcs, points, bbox
            StringBuilder arc = new StringBuilder();
            Formatter arcFmt = new Formatter(arc);
            StringBuilder point = new StringBuilder();
            Formatter pointFmt = new Formatter(point);
            StringBuilder obj = new StringBuilder();
            Formatter objFmt = new Formatter(obj);
            
            // Count constellations
            ++constellationCount;
            
            // First, decide on bounding box
            for( Map.Entry<String,ConstlStar> entry: cons.getCStars().entrySet() )
            {
                // Determine bounding box, gets flipped after
                //   for browser (as [0,0] at top left not bottom left)
                ConstlStar star = entry.getValue();
                double x = star.getProjection4().getX();
                double y = star.getProjection4().getY();
                
                if( x < boxX0 ) { boxX0 = x; }
                // Whole universe, don't bother with max Y, this will be -175. (see arcs below)
                if( !wholeUniverse && y < boxY0 ) { boxY0 = y; }
                if( x > boxX1 ) { boxX1 = x; }
                // Whole universe, don't bother with max Y, this will be 175. (see arcs below)
                if( !wholeUniverse && y > boxY1 ) { boxY1 = y; }
            }
            
            // Next, now we have the bounds,set the coords
            for( Map.Entry<String,ConstlStar> entry: cons.getCStars().entrySet() )
            {
                // Determine bounding box, gets flipped after
                //   for browser (as [0,0] at top left not bottom left)
                ConstlStar star = entry.getValue();
                double x = star.getProjection4().getX();
                double y = star.getProjection4().getY();
                
                /* Arcs.
                 * Note here, we assume projected 'heights' [y] are -175 to 175
                 * which maps to a RightAsc of greater than 89.67 deg but not
                 * to the poles themselves (infinity!)
                 */
                double xproj = x-boxX0;
                double yproj = y-boxY0;
                star.setXproj(x); star.setYproj(y);
                arcFmt.format("[%f,%f],",x,y);
                pointFmt.format("{\"type\":\"Point\",\"coordinates\":[%f,%f],\"id\":\"%s\"},",x,y,star.getDisplayName());
            }       
                
            // Multiline string (objs)
            objFmt.format("{\"type\":\"MultiLineString\",\"arcs\":[[%d]],\"id\":\"%d\",\"properties\":{\"name\":\"%s\",\"type\":\"%s\"}},",
                       constellationCount,constellationCount, cons.getName(), (cons.getZodiac()?"Zodiac":"NonAstro") );
            objs += point.toString().concat(obj.toString());
            // Arcs: Remove last comma, wrap with []
            arc.setLength(arc.length()-1);
            arcs += ARRAYST.concat(arc.toString()).concat(ARRAYEND);
            
            // Reuse classes
            arcFmt.close();
            arc.setLength(0);
            pointFmt.close();
            point.setLength(0);
            objFmt.close();
        }
        
        // Bounding box
        //bboxFmt.format( "{\"type\":\"Topology\",\"bbox\":[%f,%f,%f,%f],\"arcs\":", 0., 0., boxX1-boxX0, boxY1-boxY0 );
        bboxFmt.format( "{\"type\":\"Topology\",\"bbox\":[%f,%f,%f,%f],\"arcs\":", boxX0, boxY0, boxX1, boxY1 );
        // Remove last commas
        arcs = arcs.substring(0,arcs.length()-1);
        objs = objs.substring(0,objs.length()-1);
        
        // Build topoOut
        String objsSt = "\"objects\":{\"constellations\":{\"type\":\"GeometryCollection\",\"geometries\":[";
        String objsEnd = "]}}}";
        String topoOut = bbox.toString()
                         .concat(ARRAYST)
                         .concat(arcs)
                         .concat(ARRAYEND)
                         .concat(objsSt)
                         .concat(objs)
                         .concat(objsEnd);
        
        /*
         * Write to topoJson file
         */
        System.out.println(">>>\n" +topoOut);
//        try( BufferedWriter bw = new BufferedWriter(new FileWriter(topoJsonFile)) )
//        {
//            bw.write(topoOut);
//        }
    }
}


/**
 * Filter CSV on read (Magnitude).
 * 
 * @author Chris Powell, Discoveri OU
 */
class FilterHYGrows implements CsvToBeanFilter
{
    float maxMagnitude;
    MappingStrategy strategy;
    
    /**
     * Constructor.
     * 
     * @param strategy
     * @param maxMagnitude 
     */
    public FilterHYGrows( MappingStrategy strategy, float maxMagnitude )
    {
        this.strategy = strategy;
        this.maxMagnitude = maxMagnitude;
    }
    
    /**
     * Filter.
     * 
     * @param line
     * @return 
     */
    @Override
    public boolean allowLine(String[] line)
    {
        // Get Magnitude column
        int idx = strategy.getColumnIndex("mag");
        // Check if constellation field empty (eg: Sun, unallocated etc.)
        if( line[strategy.getColumnIndex("con")].isEmpty() ) return false;
    
        // Only return those stars brighter than given value
        return Double.parseDouble(line[idx]) < maxMagnitude;
    }
}


/**
 * Filter CSV on read (Magnitude).
 * 
 * @author Chris Powell, Discoveri OU
 */
class FilterSACrows implements CsvToBeanFilter
{
    float maxMagnitude;
    MappingStrategy strategy;
    
    /**
     * Constructor.
     * 
     * @param strategy
     * @param maxMagnitude 
     */
    public FilterSACrows( MappingStrategy strategy, float maxMagnitude )
    {
        this.strategy = strategy;
        this.maxMagnitude = maxMagnitude;
    }
    
    /**
     * Filter.
     * 
     * @param line
     * @return 
     */
    @Override
    public boolean allowLine(String[] line)
    {
        // Get Magnitude column
        int idx = strategy.getColumnIndex("mag");
        // Constellation empty?
        int cidx = strategy.getColumnIndex("con");
    
        // Only return those stars brighter than given value
        return Double.parseDouble(line[idx]) < maxMagnitude;
    }
}