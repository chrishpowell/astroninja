//<editor-fold defaultstate="collapsed" desc="Copyright">
/*
 * Copyright 2012 Chris Powell, CPgraph Ltd..
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
//</editor-fold>
package eu.discoveri.astroninja.utils;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.xml.sax.SAXParseException;
import java.io.File;

import eu.discoveri.astroninja.oodb.ServerAdministration;
import java.io.IOException;

/**
 * Read the persistence XML file
 *
 * @author Chris Powell, Discoveri OU
 * @email info@astrology.ninja
 */
public class PersistenceXML
{
    static final String PERSISTENCE_XML_FILEPATH = "META-INF/persistence.xml";

    /**
     * For the given persistence unit, this does a simple check that the
     * database specified in the versant.connectionURL property exists.
     *
     * @param pXMLfile the persistence XML file.  Either complete file path or
     *  within META-INF of calling class JAR file
     * @param persistenceUnit (eg: discoveri_PU)
     * @param connectionURL (eg: objectdb://localhost/test.odb or admin@localhost [Versant] or jdbc:oracle:thin:@localhost:1521:ORCL)
     * @return <code>true</code> if property exists, <code>false</code> otherwise
     * @throws IOException
     */
    public static boolean checkDatabase( String pXMLfile, String persistenceUnit, String connectionURL )
            throws IOException
    {
        String db = getDatabaseURLFromPersistenceUnit( pXMLfile, persistenceUnit, connectionURL );
        return db != null ? ServerAdministration.verifyDatabaseExists(db) : false;
    }

    /**
     * This reads the persistence.xml file and does a lookup for
     * the database connection URL value for the given persistence unit.
     * It returns the value (i.e., database@hostname) of the property
     * versant.connectionURL. Returns null if the persistence unit and/or
     * property is not found.
     *
     * @param pXMLfile the persistence XML file.  Either complete file path or
     *  within META-INF of calling class JAR file
     * @param persistenceUnit (eg: discoveri_PU)
     * @param connectionURL (eg: objectdb://localhost/test.odb or admin@localhost [Versant] or jdbc:oracle:thin:@localhost:1521:ORCL)
     * @return The database URL for a given PU
     */
    public static String getDatabaseURLFromPersistenceUnit( String pXMLfile, String persistenceUnit, String connectionURL )
    {
        String databaseURL = null;
        try
        {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = null;

            // Get persistence unit data
            if( pXMLfile != null && !"".equals(pXMLfile) )
            { // ...Got a straightforward file
                doc = docBuilder.parse( new File(pXMLfile) );
            }
            else
            { // ...Try to get this from the calling class JAR embedded META-INF (may NOT work in MT env.)
                doc = docBuilder.parse( Thread.currentThread().getContextClassLoader().getResourceAsStream(PERSISTENCE_XML_FILEPATH) );
            }

            // Loop over persistence unit XML
            NodeList units = doc.getElementsByTagName( "persistence-unit" );
            unitsLoop:
            for( int u = 0; u < units.getLength(); u++ )
            {
                Element unit = (Element) units.item(u);
                if( unit.getAttribute("name").equalsIgnoreCase(persistenceUnit) )
                {
                    NodeList properties = unit.getElementsByTagName("property");
                    for (int p = 0; p < properties.getLength(); p++)
                    {
                        Element property = (Element) properties.item(p);
                        if( property.getAttribute("name").equalsIgnoreCase(connectionURL) )
                        {
                            databaseURL = property.getAttribute("value");
                            break unitsLoop;
                        }
                    }
                }
            }
        }
        catch( SAXParseException err )
        {
            System.out.println("Parsing error, line " +err.getLineNumber()+ ", uri " +err.getSystemId());
            System.out.println(err.getMessage());
        }
        // @Todo: Catch exceptions properly
        catch( Throwable t )
        {
            t.printStackTrace();
        }
        
        return databaseURL;
    }
}

