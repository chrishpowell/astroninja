/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */
package eu.discoveri.astroninja.astronomy;

import eu.discoveri.astroninja.exception.DuplicateStarException;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class Constellation
{
    private final String name;
    private final boolean zodiac;  // In the Zodiac?  (Plus Ophiucus possibly?)
    
    // Map of stars, Star name (whichever chosen) and star details
    private final Map<String,ConstlStar> cStars = new HashMap<>();
    // Link between stars for drawing constellation
    private final List<StarPair> linkStars = new ArrayList<>();
    // List of points for drawing boundary
    private final List<Point2D> boundary = new ArrayList<>();
    

    /**
     * Constructor (assumed not in zodiac).
     * 
     * @param name 
     */
    public Constellation( String name )
    {
        this(name,false);
    }
    /**
     * Constructor.
     * 
     * @param name
     * @param zodiac flag if in zodiac
     */
    public Constellation( String name, boolean zodiac )
    {
        this.zodiac = zodiac;
        this.name = name;
    }
    
    /**
     * Get name.
     * 
     * @return 
     */
    public String getName() {  return name;  }
    
    /**
     * Is in Zodiac?
     * @return
     */
    public boolean getZodiac() { return zodiac; }
    
    /**
     * Add a star to map.
     * 
     * @param cStar 
     * @return Star
     * @throws DuplicateStarException
     */
    public ConstlStar addStar( ConstlStar cStar )
            throws DuplicateStarException
    {
        String sName = (!cStar.getProperName().isEmpty()) ? cStar.getProperName() : (!cStar.getSubsName().isEmpty() ? cStar.getSubsName() : cStar.getConstellation()+":"+cStar.getId() );
        String sSubsName = cStar.getSubsName();
        
        if( cStars.containsKey(sName) )
            throw new DuplicateStarException(sName);
        
        cStars.put(sName,cStar);
        return cStar;
    }
    
    /**
     * Get Stars list.
     * 
     * @return 
     */
    public Map<String,ConstlStar> getCStars()
    {
        return cStars;
    }
    
    /**
     * Link stars (for drawing constellation)
     * 
     * @param s1
     * @param s2 
     */
    public void linkStars( ConstlStar s1, ConstlStar s2 )
    {
        linkStars.add(new StarPair(s1,s2));
    }
    
    /**
     * Get linked stars (for drawing the constellation)
     * @return 
     */
    public List<StarPair> getLinkedStars()
    {
        return linkStars;
    }
    
    /**
     * Boundary points (must be closed loop).
     * 
     * @param point 
     */
    public void addBoundaryPoint( Point2D point )
    {
        boundary.add(point);
    }
    
    /**
     * Get constellation boundary.
     * 
     * @return 
     */
    public List<Point2D> getBoundary()
    {
        return boundary;
    }
    
    /**
     * Output list of stars
     */
    public void dump()
    {
        System.out.println("\nConstellation: " +name);
        
        // Stars
        System.out.println("  Entered stars (" +cStars.size()+ ")>");
        cStars.forEach((k,star)->{
            System.out.println("  Name(" +star.getId()+ ") [" +star.getSubsName()+
                               "/" +star.getProperName()+
                               ((star.getSubsName().isEmpty()&&star.getProperName().isEmpty())?"/"+name+":"+star.getId():"")+
                               "] RightAsc: " +star.getRA()+
                               ", Decl.: " +star.getDecl()+
                               ", Dist (parsecs): " +star.getDistParsecs()+
                               ", Mag: " +star.getMagnitude()     );
        });
        
        // Links
        System.out.println("  To draw>");
        linkStars.forEach(sp->{
            System.out.println("  [" +sp.getS1().getSubsName()+ "/" +sp.getS1().getProperName()+
                               "] to [" +sp.getS2().getSubsName()+ "/" +sp.getS2().getProperName() +"]" ); 
        });
    }
}
