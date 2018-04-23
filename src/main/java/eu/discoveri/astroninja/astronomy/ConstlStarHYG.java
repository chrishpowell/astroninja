/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.astronomy;

import eu.discoveri.astroninja.astronomy.projection.Celestial2DProjection;
import com.jhlabs.map.proj.GnomonicAzimuthalProjection;
import com.opencsv.bean.CsvBindByName;
import java.util.concurrent.atomic.AtomicLong;


/**
 * Bean to read HYG csv file.
 * 
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class ConstlStarHYG implements ConstlStar
{
    /*
     * Set of attributes from HYG CSV
     */
    /**
     * ID from CSV.  NB: If manually generating a star it will be a long.
     * Careful when mixing CSV and manual.
     */
    @CsvBindByName(column="id", required=false)
    private String id;
    
    @CsvBindByName(column="bf", required=false)
    private String bayerFlamsteed;
    
    @CsvBindByName(column="proper", required=false)
    private String properName;
    
    @CsvBindByName(column="ra", required=false)
    private float ra;
    
    @CsvBindByName(column="dec", required=true)
    private float decl;
    
    @CsvBindByName(column="dist", required=true)
    private float distParsecs;
    
    @CsvBindByName(column="mag", required=true)
    private float magnitude;
    
    @CsvBindByName(column="absmag", required=false)
    private float absmag;
    
    @CsvBindByName(column="x", required=false)
    private float x;
    
    @CsvBindByName(column="y", required=false)
    private float y;
    
    @CsvBindByName(column="z", required=false)
    private float z;
    
    @CsvBindByName(column="rarad", required=false)
    private float rarad;
    
    @CsvBindByName(column="decrad", required=false)
    private float decrad;
    
    @CsvBindByName(column="con", required=false)
    private String constellation;

    // Other attributes
    private GnomonicAzimuthalProjection gap;
    private boolean project;  // Used to flag if this start will be used in projection
    private double xproj, yproj;
    
    // Generated ID
    private static long baseAtom = 0;
    private static final AtomicLong ID = new AtomicLong(baseAtom);
    
    /*
     * Constructors
     */
    public ConstlStarHYG(){}
    /**
     * Constructor without Subs name.  (Assumes all will be projected)
     * 
     * @param name
     * @param ra
     * @param decl
     * @param dist
     * @param magnitude 
     */
    public ConstlStarHYG( String name, float ra, float decl, float dist, float magnitude )
    {
        this(name,"",ra,decl,dist,magnitude, true, 0l);
    }
    /**
     * Constructor without Subs name.
     * @param name
     * @param ra
     * @param decl
     * @param dist
     * @param magnitude
     * @param project 
     */
    public ConstlStarHYG( String name, float ra, float decl, float dist, float magnitude, boolean project )
    {
        this(name,"",ra,decl,dist,magnitude,project,0l);
    }
    
    /**
     * Constructor with Subs name, project=true, basId=0.
     * 
     * @param name
     * @param subsName
     * @param ra
     * @param decl
     * @param dist
     * @param magnitude 
     */
    public ConstlStarHYG( String name, String subsName, float ra, float decl, float dist, float magnitude )
    {
        this(name,subsName,ra,decl,dist,magnitude,true,0l);
    }
    /**
     * Constructor.
     * @param name
     * @param subsName Normally Bayer-Flamsteed or similar
     * @param ra
     * @param decl
     * @param dist
     * @param magnitude 
     * @param project  Determines whether this star will be used in projection
     * @param baseAtom Base of ID (default zero).
     */
    public ConstlStarHYG( String name, String subsName, float ra, float decl, float dist, float magnitude, boolean project, long baseAtom )
    {
        id = Long.toHexString(ID.incrementAndGet());
        bayerFlamsteed = subsName;
        properName = name;
        this.ra = ra;
        this.decl = decl;
        distParsecs = dist;
        this.magnitude = magnitude;
        this.project = project;
        ConstlStarHYG.baseAtom = baseAtom;
    }

    
    /*
     * Mutators
     */
    /**
     * Get the proper name.
     * 
     * @return name
     */
    @Override
    public String getProperName() { return properName; }

    /**
     * Get Right Ascension (degrees).
     * 
     * @return 
     */
    @Override
    public float getRA() { return ra; }

    /**
     * Get declination (degrees).
     * 
     * @return 
     */
    @Override
    public float getDecl() { return decl; }
    
    /**
     * Get magnitude.
     * 
     * @return 
     */
    @Override
    public float getMagnitude() {  return magnitude;  }
    
    /**
     * Get RA in radians.
     * 
     * @return RA
     */
    public float getRARads()
    {
        return (float)Math.toRadians(ra);
    }
    
    /**
     * Get declination in radians.
     * 
     * @return declination
     */
    public float getDeclRads()
    {
        return (float)Math.toRadians(decl);
    }
    
    // Getters
    @Override
    public String getId() { return id; }
    
    /**
     * Get subs name
     * @return 
     */
    @Override
    public String getSubsName() { return bayerFlamsteed; }
    
    /**
     * Get some sort of name (if proper and subs name are null, for example)
     * @return 
     */
    @Override
    public String getDisplayName()
    {
        if( bayerFlamsteed.isEmpty() && properName.isEmpty() )
            { return constellation+":"+getId(); }
        else
        if( !bayerFlamsteed.isEmpty() )
            { return bayerFlamsteed; }
        
        return properName;
    }
    
    public float getAbsmag() { return absmag; }
    public float getX() { return x; }
    public float getY() { return y; }
    public float getZ() { return z; }
    public float getRArad() { return rarad; }
    public float getDecrad() { return decrad; }
    public boolean isProject() { return project; }

    /**
     * Get the (browser) projected X coord
     * @return 
     */
    public double getXproj() { return xproj; }
    /**
     * Get the (browser) projected Y coord
     * @return 
     */
    public double getYproj() { return yproj; }
    
    /**
     * Get distance to star
     * @return 
     */
    @Override
    public float getDistParsecs() { return distParsecs; }
    
    /**
     * Get star's constellation
     * @return 
     */
    @Override
    public String getConstellation() { return constellation; }
    

    /*
     * Setters
     */
    public void setId(String id) { this.id = id; }
    public void setBayerFlamsteed(String bayerFlamsteed) { this.bayerFlamsteed = bayerFlamsteed; }
    public void setProperName(String properName) { this.properName = properName; }
    public void setRA(float ra) { this.ra = ra; }
    public void setDecl(float decl) { this.decl = decl; }
    public void setDistParsecs(float distParsecs) { this.distParsecs = distParsecs; }
    public void setMagnitude(float magnitude) { this.magnitude = magnitude; }
    public void setAbsmag(float absmag) { this.absmag = absmag; }
    public void setX(float x) { this.x = x; }
    public void setY(float y) { this.y = y; }
    public void setZ(float z) { this.z = z; }
    public void setRArad(float rarad) { this.rarad = rarad; }
    public void setDecrad(float decrad) { this.decrad = decrad; }
    public void setConstellation(String constellation) { this.constellation = constellation; }
    public void setProject(boolean project) { this.project = project; }

    /**
     * Set the (browser) X coord
     * @param xproj 
     */
    @Override
    public void setXproj(double xproj) { this.xproj = xproj; }
    /**
     * Set the (browser) Y coord
     * @param yproj 
     */
    @Override
    public void setYproj(double yproj) { this.yproj = yproj; }
    
    /**
     * Gnomonic Projection
     * @return 
     */
    @Override
    public java.awt.geom.Point2D.Double getProjection()
    {
        java.awt.geom.Point2D.Double xy = new java.awt.geom.Point2D.Double(0.0,0.0);
        return gap.project(Math.toRadians(ra*15),Math.toRadians(decl),xy);
    }
    
    /**
     * Projection2
     * @return 
     */
    @Override
    public javafx.geometry.Point2D getProjection2()
    {
        return Celestial2DProjection.EquatorialToCartesian( 0., 0.,
                                                            175, 140,
                                                            1.0, 
                                                            ra*15.0, decl );
    }
    
    /**
     * Projection3
     * @return 
     */
    @Override
    public javafx.geometry.Point2D getProjection3()
    {
        return Celestial2DProjection.EquatorialToCartesian1( 0., 0.,
                                                            175, 140,
                                                            1.0, 
                                                            ra*15.0, decl );
    }

    /**
     * Projection3
     * @return 
     */
    @Override
    public javafx.geometry.Point2D getProjection4()
    {
        return Celestial2DProjection.EquatorialToCartesian1( 0., 0.,
                                                            175, 140,
                                                            1.0, 
                                                            ra*15.0, decl );
    }
}
