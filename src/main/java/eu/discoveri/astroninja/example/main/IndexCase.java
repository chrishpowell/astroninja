/*
 * Copyright (C) 2018 Chris Powell, Discoveri OU
 */

package eu.discoveri.astroninja.example.main;

import eu.discoveri.astroninja.example.entity.AUser;
import eu.discoveri.astroninja.example.entity.Languages;
import eu.discoveri.astroninja.exception.InvalidISOCodeLengthException;
import eu.discoveri.astroninja.location.Coordinates;
import eu.discoveri.astroninja.location.Country;
import eu.discoveri.astroninja.location.CtrySubDiv;
import eu.discoveri.astroninja.location.Location;
import eu.discoveri.astroninja.location.Location.Continent;
import eu.discoveri.astroninja.location.Location.Hemisphere;

import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 * https://en.wikipedia.org/wiki/ISO_3166-1
 * SubDiv languages, eg: https://www.iso.org/obp/ui/#iso:code:3166:AF
 * 
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class IndexCase
{
    public static void main(String[] args)
            throws MalformedURLException, InvalidISOCodeLengthException
    {
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("astroindextest_PU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        
        // Some languages (Entities)
        Languages farsi = new Languages("fa","Persian","","Farsi");
        em.persist(farsi);
        Languages pashto = new Languages("ps","Pashto","","");
        em.persist(pashto);
        Languages serbian = new Languages("sr","Serbian","","");
        em.persist(serbian);
        
        Set<Languages> afghanLangs = new HashSet<>();
        afghanLangs.add(farsi);
        afghanLangs.add(pashto);
        Set<Languages> montenegroLangs = new HashSet<>();
        montenegroLangs.add(serbian);
        
        // Some Country subdivisions (Embedded)
        CtrySubDiv csdAFBDS = new CtrySubDiv("AF-BDS","Badakshan",afghanLangs);
        Set<CtrySubDiv> afghanSubDivs = new HashSet<>();
        afghanSubDivs.add(csdAFBDS);
        
        CtrySubDiv csdMEBAR = new CtrySubDiv("ME-01","Bar",montenegroLangs);
        CtrySubDiv csdPDG = new CtrySubDiv("ME-16","Podgorica",montenegroLangs);
        Set<CtrySubDiv> montenegroSubDivs = new HashSet<>();
        montenegroSubDivs.add(csdMEBAR);
        montenegroSubDivs.add(csdPDG);
        
        // Some Countries (Entity)
        Country ctry1 = new Country("AF", afghanSubDivs, "004", "Afghanistan");
        em.persist(ctry1);
        Country ctry2 = new Country("ME", montenegroSubDivs, "499", "Montenegro");  // ME-16 Podgorica
        em.persist(ctry2);
        
        // PDG coords (Embedded): Use http://en.mygeoposition.com/, drag pin to location, enter Lat/Lon as doubles
        Coordinates coordsPDG = new Coordinates(42.4444294d,19.2743270d);
        
        // Location (Embedded)
        Location locnPDG = new Location(Hemisphere.NORTH,Continent.EUROPE,"Southern Europe",ctry2,"Podgorica",coordsPDG);

            // User
        AUser me = new AUser("Chris","Powell",locnPDG);
        em.persist(me);
        
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
